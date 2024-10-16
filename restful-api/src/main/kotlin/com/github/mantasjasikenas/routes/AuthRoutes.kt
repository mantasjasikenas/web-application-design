package com.github.mantasjasikenas.routes

import com.github.mantasjasikenas.docs.auth.*
import com.github.mantasjasikenas.model.*
import com.github.mantasjasikenas.model.auth.LoginDto
import com.github.mantasjasikenas.model.auth.SuccessfulLoginDto
import com.github.mantasjasikenas.model.user.PostUserDto
import com.github.mantasjasikenas.model.user.toSuccessfulRegisterDto
import com.github.mantasjasikenas.service.UserService
import com.github.mantasjasikenas.util.extractSubject
import io.github.smiley4.ktorswaggerui.dsl.routing.post
import io.github.smiley4.ktorswaggerui.dsl.routing.route
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import kotlin.time.Duration.Companion.days


fun Route.authRoutes(userService: UserService) {
    route("/auth", authRoutesDocs()) {
        post("/register", registerDocs()) {
            val postUserDto = call.receive<PostUserDto>()

            userService.findByUsername(postUserDto.username)?.let {
                return@post call.respondCustom(
                    message = "User with username ${postUserDto.username} already exists",
                    status = HttpStatusCode.UnprocessableEntity
                )
            }

            val createdUser = userService.newAndAssignRole(postUserDto, Role.User) ?: return@post call.respondCustom(
                message = "Failed to create user",
                status = HttpStatusCode.UnprocessableEntity
            )

            call.respondCreated(
                "User created successfully",
                createdUser.toSuccessfulRegisterDto()
            )
        }

        post("/login", loginDocs()) {
            val loginDto = call.receive<LoginDto>()

            val authResponse: AuthResponse? = userService.authenticate(loginDto)

            if (authResponse == null) {
                call.respondCustom(
                    message = "Username or password is incorrect",
                    status = HttpStatusCode.UnprocessableEntity
                )

                return@post
            }

            call.appendRefreshTokenCookie(authResponse.refreshToken)

            call.respondSuccess(
                message = "Login successful",
                data = SuccessfulLoginDto(
                    accessToken = authResponse.accessToken
                )
            )
        }

        post("/accessToken", accessTokenDocs()) {
            val refreshToken = call.request.cookies["RefreshToken"] ?: return@post call.respondCustom(
                message = "Refresh token not found",
                status = HttpStatusCode.UnprocessableEntity
            )

            val authResponse = userService.refreshToken(token = refreshToken)

            if (authResponse == null) {
                call.respondCustom(
                    message = "Unprocessable entity",
                    status = HttpStatusCode.UnprocessableEntity
                )

                return@post
            }

            call.appendRefreshTokenCookie(authResponse.refreshToken)

            call.respondSuccess(
                message = "Access token refreshed successfully",
                data = SuccessfulLoginDto(
                    accessToken = authResponse.accessToken
                )
            )
        }

        authenticate {
            post("/logout", logoutDocs()) {
                val userId = call.extractSubject() ?: return@post call.respondCustom(
                    message = "Failed to logout",
                    status = HttpStatusCode.UnprocessableEntity
                )

                if (!userService.logout(userId)) {
                    return@post call.respondCustom(
                        message = "Failed to logout",
                        status = HttpStatusCode.UnprocessableEntity
                    )
                }

                call.respondSuccess<Unit>("Logout successful")
            }
        }
    }
}

private fun ApplicationCall.appendRefreshTokenCookie(refreshToken: String) {
    this.response.cookies.append(
        name = "RefreshToken",
        value = refreshToken,
        maxAge = 30.days.inWholeSeconds,
        httpOnly = true,
        //secure = true
    )
}
