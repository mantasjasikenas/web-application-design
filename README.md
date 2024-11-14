<div align="center">
  <img src="./docs/assets/logo.svg" alt="Shortcuts logo" width="75">  
  
  <h1>Tornado - užduočių valdymo sistema</h1>
</div>

## Turinys

- [Turinys](#turinys)
- [Sprendžiamo uždavinio aprašymas](#sprendžiamo-uždavinio-aprašymas)
  - [Sistemos paskirtis](#sistemos-paskirtis)
  - [Funkciniai reikalavimai](#funkciniai-reikalavimai)
- [Taikomosios srities objektai](#taikomosios-srities-objektai)
- [Sistemos architektūra](#sistemos-architektūra)
  - [Sistemos sudedamosios dalys](#sistemos-sudedamosios-dalys)
  - [Diegimo diagrama](#diegimo-diagrama)
- [Naudotojo sąsajos projektas](#naudotojo-sąsajos-projektas)
- [API specifikacija](#api-specifikacija)
  - [Autentifikacijos API metodai](#auth-endpoints)
  - [Projektų API metodai](#projects-endpoints)
  - [Sekcijų API metodai](#sections-endpoints)
  - [Užduočių API metodai](#tasks-endpoints)
- [Projekto išvados](#projekto-išvados)

## Sprendžiamo uždavinio aprašymas

### Sistemos paskirtis

Projekto tikslas – sukurti užduočių valdymo įrankį, kuris leistų prisijungusiems sistemos naudotojams efektyviai
planuoti, vykdyti ir stebėti savo užduotis. Sistema leis valdyti projektus, sekcijas, užduotis ir naudotojų paskyras.

Aukščiausiame hierarchijos lygyje yra projektai, kurie suskirstomi į sekcijas, o sekcijose kuriamos užduotys. Projektai
grupuoja platesnius tikslus, sekcijos leidžia smulkiau suskirstyti veiklas pagal temas ar etapus, o užduotys yra
konkretūs darbai, kuriuos reikia atlikti.

Sistemai naudotojai bus trijų rolių: svečias, registruotas naudotojas ir administratorius.

### Funkciniai reikalavimai

#### Svečias galės

1. Peržiūrėti prisijungimo puslapį
2. Peržiūrėti registracijos puslapį
3. Prisijungti prie sistemos
4. Užsiregistruoti į sistemą

#### Registruotas sistemos naudotojas galės

1. Prisijungti prie sistemos
2. Atsijungti nuo sistemos
3. Sukurti, ištrinti, redaguoti, peržiūrėti projektus
4. Sukurti, ištrinti, redaguoti, peržiūrėti sekcijas
5. Sukurti, ištrinti, redaguoti, peržiūrėti užduotis

#### Administratorius galės

1. Prisijungti prie sistemos
2. Atsijungti nuo sistemos
3. Valdyti visus projektus, sekcijas, užduotis

## Taikomosios srities objektai

Taikomosios srities objektus sieja hierarchinis ryšys: `Projektas` ⇒ `Sekcija` ⇒ `Užduotis`

- Projektas (project)
  - tai aukščiausio lygio objektas, kuris grupuoja sekcijas ir užduotis. Projektas turi pavadinimą, aprašymą, sukūrimo datą ir sukūrėją.
- Sekcija (section)
  - tai objektas, kuris priklauso tam tikram projektui. Sekcija turi pavadinimą, sukūrimo datą ir sukūrėją.
- Užduotis (task)
  - tai objektas, kuris priklauso tam tikrai sekcijai. Užduotis turi pavadinimą, aprašymą, prioritetą, atlikimo būseną, atlikimo terminą, sukūrimo datą ir sukūrėją.
- Naudotojas (user)
  - tai objektas, kuris turi prisijungimo duomenis, tokius kaip el. paštas, slapyvardis ir slaptažodis.

## Sistemos architektūra

### Sistemos sudedamosios dalys

- Kliento pusė (angl. Front-end) – bus realizuojama naudojant `SvelteKit` karkasą. SvelteKit - tai Svelte paremtas
  karkasas, kuris remiasi Svelte pagrindais ir suteikia galingų funkcijų, tokių kodo skaidymas, failų maršrutizavimas, kurios palengvina sudėtingų programų kūrimą.
- Serverio pusė (angl. Back-end) – bus realizuojama naudojant `Ktor` karkasą. Ktor yra asinchroninis karkasas, skirtas
  mikroservisams, žiniatinklio programoms ir kt. kurti, naudojant Kotlin programavimo kalbą.
- Duomenys bus saugomi `PostgreSQL` duomenų bazėje, kuri bus pasiekiama per `Exposed ORM`. Duomenų bazė yra reliacinė,
  kuri leidžia saugoti duomenis lentelėse bei sudaryti ryšius tarp jų.

### Diegimo diagrama

Klientinė dalis bus talpinama `Vercel` platformoje, o serverinė dalis, API ir duomenų bazė, bus talpinama `DigitalOcean App Platform` platformoje.

![Diegimo diagrama](./docs/assets/deployment_diagram.png)

## Naudotojo sąsajos projektas

Toliau pateikiami realizuotų langų ekrano iškarpos ir juos atitinkantys wireframe`ai. Prototipai sukurti naudojant [OpenSource shadcn/ui - kit](https://www.figma.com/community/file/1426161867268046394/opensource-shadcn-ui-kit-for-figma) šabloną, naudojantis **Figma** įrankiu. Prototipai buvo sukurti tik pagrindiniams langams.

### Pradinis puslapis

Pradinis puslapis yra pirmas puslapis, kurį matys naudotojas atėjęs į svetainę. Jame bus pateikta trumpa informacija apie sistemą, prisijungimo ir registracijos mygtukai. Šis puslapis matomas tik svečiams.

![Website](docs/assets/website/home.png)

### Registracijos puslapis

Registracijos puslapyje naudotojas gali užsiregistruoti į sistemą. Registracijos formoje svečias turi suvesti savo slapyvardį, el. paštą ir slaptažodį.

|              Realizuotas langas               |                    Prototipas lango                     |
| :-------------------------------------------: | :-----------------------------------------------------: |
| ![alt text](docs/assets/website/register.png) | ![alt text](docs/assets/website/register-prototype.png) |

### Prisijungimo puslapis

Prisijungimo puslapyje naudotojas gali prisijungti prie sistemos. Prisijungimo formoje naudotojas turi suvesti savo el. paštą ir slaptažodį.

|            Realizuotas langas             |                   Prototipas lango                   |
| :---------------------------------------: | :--------------------------------------------------: |
| ![Website](docs/assets/website/login.png) | ![alt text](docs/assets/website/login_prototype.png) |

### Projektų informacijos langas

Projektų informacijos lange bus pateikta informacija apie projektą, jo pavadinimą, aprašymą, sukūrimo datą ir sukūrėją. Taip pat yra galimybė redaguoti projektą arba jį ištrinti.

|              Realizuotas langas              |                    Prototipas lango                     |
| :------------------------------------------: | :-----------------------------------------------------: |
| ![Website](docs/assets/website/projects.png) | ![alt text](docs/assets/website/projects_prototype.png) |

### Projekto sukūrimo modalinis langas

Projekto sukūrimo modale naudotojas gali sukurti naują projektą. Sukūrus projektą, jis bus matomas projekto informacijos lange. Projekto sukūrimo modale naudotojas turi suvesti projekto pavadinimą ir aprašymą. Identiškai atrodys ir projekto redagavimo modalinis langas.

![Website](docs/assets/website/create_project.png)

### Užduočių puslapis

Užduočių puslapyje pateikta informacija apie visas užduotis, kurios yra priskirtos tam tikrai sekcijai. Užduočių puslapyje galima peržiūrėti užduotis, sukurti naują užduotį, redaguoti esamą užduotį arba ją ištrinti.

|            Realizuotas langas             |                   Prototipas lango                   |
| :---------------------------------------: | :--------------------------------------------------: |
| ![Website](docs/assets/website/tasks.png) | ![alt text](docs/assets/website/tasks_prototype.png) |

### Užduoties informacijos langas

Paspaudus ant užduoties, atidaromas užduoties informacijos langas, kuriame pateikta informacija apie užduotį, jos pavadinimą, aprašymą, prioritetą, atlikimo būseną, atlikimo terminą.

![Website](docs/assets/website/task_details.png)

### Sekcijos sukūrimo modalinis langas

Sekcijos sukūrimo modale naudotojas gali sukurti naują sekciją. Sukūrus sekciją, ji atsidaras užduočių puslapyje. Sekcijos sukūrimo modale naudotojas turi suvesti sekcijos pavadinimą. Identiškai atrodys ir sekcijos redagavimo modalinis langas.

![Website](docs/assets/website/create_section.png)

## API specifikacija

API specifikacija bus aprašyta naudojant `OpenAPI` standartą. Pilnas API aprašas gali būti rastas [čia](./docs/assets/openapi.json). Specifikacija pateikiama anglų kalba.

---

### Auth Endpoints

#### Register new user

**POST** `/api/v1/auth/register`

| Parameter | In   | Type   | Required | Description   |
| --------- | ---- | ------ | -------- | ------------- |
| email     | body | string | true     | User email    |
| username  | body | string | true     | User username |
| password  | body | string | true     | User password |

**Request Body Example:**

```json
{
  "username": "Sigmund",
  "email": "sigmund@gmail.com",
  "password": "VerySafePassword132"
}
```

**Response Example:**

```json
{
  "success": true,
  "status": "201",
  "message": "User created successfully",
  "data": {
    "id": "9351f427-fba2-4378-99e9-2b82dc2a9466",
    "userName": "Sigmund",
    "email": "sigmund@gmail.com"
  }
}
```

**Responses:**

| Status Code | Description          |
| ----------- | -------------------- |
| 201         | User created         |
| 422         | Unprocessable entity |

---

#### Login user

**POST** `/api/v1/auth/login`

| Parameter | In   | Type   | Required | Description   |
| --------- | ---- | ------ | -------- | ------------- |
| email     | body | string | true     | User email    |
| password  | body | string | true     | User password |

**Request Body Example:**

```json
{
  "username": "Sigmund",
  "password": "VerySafePassword132"
}
```

**Response Example:**

```json
{
  "success": true,
  "status": "200",
  "message": "Login successful",
  "data": {
    "accessToken": "dgfgfgdfgdfhbgsdgsdg.dfgfgdfgdfghrtyrtrfiaXNzIjoiTWFudGFzIiwianRpIjoiNmYwMzgzYmMtNjY3MC00ZTMxLTg0N2QtMGVlZGYxNWJkZDJkIiwic3ViIjoiYzFjY2FmYTYtM2Efdgg8F81MXGox9wUMVYWwwwo"
  }
}
```

**Responses:**

| Status Code | Description          |
| ----------- | -------------------- |
| 200         | Login successful     |
| 422         | Unprocessable entity |

---

#### Refresh access token

**POST** `/api/v1/auth/accessToken`

| Parameter    | In     | Type   | Required | Description   |
| ------------ | ------ | ------ | -------- | ------------- |
| RefreshToken | cookie | string | true     | Refresh token |

**Response Example:**

```json
{
  "success": true,
  "status": "200",
  "message": "Access token refreshed successfully",
  "data": {
    "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJUcnVzdGVkQ2xpZW50IiwiaXNzIjoiTWFudGFzIiwianRpIjoiNmYwMzgzYmMtNjY3MC00ZTMxLTg0N2QtMGVlZGYxNWJkZDJkIiwic3ViIjoiYzFjY2FmYTYtM2EwNi00NzA3LThkNDQtNzc3ZWFkZWYzNjM1IiwidXNlcm5hbWUiOiJhZG1pbiIsInJvbGVzIjpbIkFkbWluIl0sImV4cCI6MTcyODkxODU0Mn0.qPQOaKwhjjfMLNriH8D35xP8F81MXGox9wUMVYWwwwo"
  }
}
```

**Responses:**

| Status Code | Description            |
| ----------- | ---------------------- |
| 200         | Access token refreshed |
| 422         | Unprocessable entity   |

---

#### Logout user

**POST** `/api/v1/auth/logout`

| Parameter | In     | Type   | Required | Description  |
| --------- | ------ | ------ | -------- | ------------ |
| Bearer    | header | string | true     | Access token |

**Response Example:**

```json
{
  "success": true,
  "status": "200",
  "message": "Logout successful"
}
```

**Responses:**

| Status Code | Description          |
| ----------- | -------------------- |
| 200         | Logout successful    |
| 422         | Unprocessable entity |

---

### Projects Endpoints

#### Get all projects

**GET** `/api/v1/projects`

| Parameter | In     | Type   | Required | Description  |
| --------- | ------ | ------ | -------- | ------------ |
| Bearer    | header | string | true     | Access token |

**Responses:**

| Status Code | Description  |
| ----------- | ------------ |
| 200         | All projects |
| 401         | Unauthorized |
| 403         | Forbidden    |

**Response Example:**

```json
{
  "success": true,
  "status": "200",
  "message": "All projects",
  "data": [
    {
      "id": 1,
      "name": "Personal Task Manager",
      "description": "Plan your day with ease",
      "createdAt": "2024-10-01T12:00:00",
      "createdBy": "Antonia"
    },
    {
      "id": 2,
      "name": "Home Budget",
      "description": "Manage your finances",
      "createdAt": "2024-08-09T12:00:00",
      "createdBy": "Anton"
    }
  ]
}
```

---

#### Create a new project

**POST** `/api/v1/projects`

| Parameter   | In     | Type   | Required | Description         |
| ----------- | ------ | ------ | -------- | ------------------- |
| Bearer      | header | string | true     | Access token        |
| name        | body   | string | true     | Project name        |
| description | body   | string | true     | Project description |

**Request Body Example:**

```json
{
  "name": "New Project",
  "description": "Project description"
}
```

**Response Example:**

```json
{
  "success": true,
  "status": "201",
  "message": "Project created",
  "data": {
    "id": 1,
    "name": "Personal Task Manager",
    "description": "Plan your day with ease",
    "createdAt": "2024-10-01T12:00:00",
    "createdBy": "Antonia"
  }
}
```

**Responses:**

| Status Code | Description          |
| ----------- | -------------------- |
| 201         | Created              |
| 400         | Bad request          |
| 422         | Unprocessable entity |
| 401         | Unauthorized         |
| 403         | Forbidden            |

---

#### Get project by id

**GET** `/api/v1/projects/{projectId}`

| Parameter | In     | Type    | Required | Description  |
| --------- | ------ | ------- | -------- | ------------ |
| Bearer    | header | string  | true     | Access token |
| projectId | path   | integer | true     | Project id   |

**Response Example:**

```json
{
  "success": true,
  "status": "200",
  "message": "Project by id",
  "data": {
    "id": 1,
    "name": "Personal Task Manager",
    "description": "Plan your day with ease",
    "createdAt": "2024-10-01T12:00:00",
    "createdBy": "Antonia"
  }
}
```

**Responses:**

| Status Code | Description   |
| ----------- | ------------- |
| 200         | Project by id |
| 400         | Bad request   |
| 404         | Not found     |
| 401         | Unauthorized  |
| 403         | Forbidden     |

---

#### Delete project by id

**DELETE** `/api/v1/projects/{projectId}`

| Parameter | In     | Type    | Required | Description  |
| --------- | ------ | ------- | -------- | ------------ |
| Bearer    | header | string  | true     | Access token |
| projectId | path   | integer | true     | Project id   |

**Response Example:**

```json
{
  "success": true,
  "status": "204",
  "message": "Project deleted"
}
```

| Parameter | In     | Type    | Required | Description  |
| --------- | ------ | ------- | -------- | ------------ |
| Bearer    | header | string  | true     | Access token |
| projectId | path   | integer | true     | Project id   |

**Responses:**

| Status Code | Description  |
| ----------- | ------------ |
| 204         | No content   |
| 400         | Bad request  |
| 404         | Not found    |
| 401         | Unauthorized |
| 403         | Forbidden    |

---

#### Update project by id

**PATCH** `/api/v1/projects/{projectId}`

| Parameter   | In     | Type    | Required | Description         |
| ----------- | ------ | ------- | -------- | ------------------- |
| Bearer      | header | string  | true     | Access token        |
| projectId   | path   | integer | true     | Project id          |
| name        | body   | string  | false    | Project name        |
| description | body   | string  | false    | Project description |

**Request Body Example:**

```json
{
  "name": "Updated Project",
  "description": "Updated description"
}
```

**Response Example:**

```json
{
  "success": true,
  "status": "200",
  "message": "Project updated",
  "data": {
    "id": 1,
    "name": "Personal Task Manager",
    "description": "Plan your day with ease",
    "createdAt": "2024-10-01T12:00:00",
    "createdBy": "Antonia"
  }
}
```

**Responses:**

| Status Code | Description          |
| ----------- | -------------------- |
| 200         | OK                   |
| 400         | Bad request          |
| 422         | Unprocessable entity |
| 404         | Not found            |
| 401         | Unauthorized         |
| 403         | Forbidden            |

---

### Sections Endpoints

#### Get all sections

**GET** `/api/v1/projects/{projectId}/sections`

| Parameter | In     | Type    | Required | Description               |
| --------- | ------ | ------- | -------- | ------------------------- |
| Bearer    | header | string  | true     | Access token              |
| projectId | path   | integer | true     | Project id                |
| withTasks | query  | boolean | false    | Include tasks in sections |

**Response Example:**

```json
{
  "success": true,
  "status": "200",
  "message": "All sections",
  "data": [
    {
      "id": 1,
      "projectId": 1,
      "name": "Backlog",
      "createdBy": "Virginia",
      "createdAt": "2024-09-01T12:00:00"
    },
    {
      "id": 2,
      "projectId": 1,
      "name": "In Progress",
      "createdBy": "Natalie",
      "createdAt": "2024-09-02T12:00:00"
    }
  ]
}
```

**Responses:**

| Status Code | Description  |
| ----------- | ------------ |
| 200         | All sections |

---

#### Create a new section

**POST** `/api/v1/projects/{projectId}/sections`

| Parameter | In     | Type    | Required | Description  |
| --------- | ------ | ------- | -------- | ------------ |
| Bearer    | header | string  | true     | Access token |
| projectId | path   | integer | true     | Project id   |
| name      | body   | string  | true     | Section name |

**Request Body Example:**

```json
{
  "name": "New Section"
}
```

**Response Example:**

```json
{
  "success": true,
  "status": "201",
  "message": "Section created",
  "data": {
    "id": 1,
    "projectId": 1,
    "name": "Backlog",
    "createdBy": "Virginia",
    "createdAt": "2024-09-01T12:00:00"
  }
}
```

**Responses:**

| Status Code | Description          |
| ----------- | -------------------- |
| 201         | Section created      |
| 400         | Bad request          |
| 404         | Not found            |
| 422         | Unprocessable entity |
| 401         | Unauthorized         |
| 403         | Forbidden            |

---

#### Get section by id

**GET** `/api/v1/projects/{projectId}/sections/{sectionId}`

| Parameter | In     | Type    | Required | Description  |
| --------- | ------ | ------- | -------- | ------------ |
| Bearer    | header | string  | true     | Access token |
| projectId | path   | integer | true     | Project id   |
| sectionId | path   | integer | true     | Section id   |

**Response Example:**

```json
{
  "success": true,
  "status": "200",
  "message": "Section by id",
  "data": {
    "id": 1,
    "projectId": 1,
    "name": "Backlog",
    "createdBy": "Virginia",
    "createdAt": "2024-09-01T12:00:00"
  }
}
```

**Responses:**

| Status Code | Description   |
| ----------- | ------------- |
| 200         | Section by id |
| 400         | Bad request   |
| 404         | Not found     |
| 401         | Unauthorized  |
| 403         | Forbidden     |

---

#### Delete section by id

**DELETE** `/api/v1/projects/{projectId}/sections/{sectionId}`

| Parameter | In     | Type    | Required | Description  |
| --------- | ------ | ------- | -------- | ------------ |
| Bearer    | header | string  | true     | Access token |
| projectId | path   | integer | true     | Project id   |
| sectionId | path   | integer | true     | Section id   |

**Response Example:**

```json
{
  "success": true,
  "status": "204",
  "message": "Section deleted"
}
```

**Responses:**

| Status Code | Description  |
| ----------- | ------------ |
| 204         | No content   |
| 400         | Bad request  |
| 404         | Not found    |
| 401         | Unauthorized |
| 403         | Forbidden    |

---

#### Update section by id

**PATCH** `/api/v1/projects/{projectId}/sections/{sectionId}`

| Parameter | In     | Type    | Required | Description  |
| --------- | ------ | ------- | -------- | ------------ |
| Bearer    | header | string  | true     | Access token |
| projectId | path   | integer | true     | Project id   |
| sectionId | path   | integer | true     | Section id   |
| name      | body   | string  | true     | Section name |

**Request Body Example:**

```json
{
  "name": "Updated Section"
}
```

**Response Example:**

```json
{
  "success": true,
  "status": "200",
  "message": "Section updated",
  "data": {
    "id": 1,
    "projectId": 1,
    "name": "Backlog",
    "createdBy": "Virginia",
    "createdAt": "2024-09-01T12:00:00"
  }
}
```

**Responses:**

| Status Code | Description          |
| ----------- | -------------------- |
| 200         | Section updated      |
| 400         | Bad request          |
| 422         | Unprocessable entity |
| 404         | Not found            |
| 401         | Unauthorized         |
| 403         | Forbidden            |

---

### Tasks Endpoints

#### Get tasks by project and section id

**GET** `/api/v1/projects/{projectId}/sections/{sectionId}/tasks`

| Parameter | In     | Type    | Required | Description  |
| --------- | ------ | ------- | -------- | ------------ |
| Bearer    | header | string  | true     | Access token |
| projectId | path   | integer | true     | Project id   |
| sectionId | path   | integer | true     | Section id   |

**Response Example:**

```json
{
  "success": true,
  "status": "200",
  "message": "All tasks",
  "data": [
    {
      "id": 1,
      "sectionId": 1,
      "name": "Finish REST API",
      "description": "Create documentation and tests",
      "priority": "Low",
      "completed": false,
      "dueDate": "2023-10-09T12:00:00",
      "createdBy": "Sigmund",
      "createdAt": "2024-10-01T12:12:32"
    },
    {
      "id": 2,
      "sectionId": 1,
      "name": "Upload to GitHub",
      "description": "Push code to GitHub",
      "priority": "Vital",
      "completed": true,
      "dueDate": "2023-10-09T12:00:00",
      "createdBy": "Viktor",
      "createdAt": "2024-10-01T12:12:32"
    }
  ]
}
```

**Responses:**

| Status Code | Description  |
| ----------- | ------------ |
| 200         | All tasks    |
| 400         | Bad request  |
| 401         | Unauthorized |
| 403         | Forbidden    |

---

#### Create a new task

**POST** `/api/v1/projects/{projectId}/sections/{sectionId}/tasks`

| Parameter   | In     | Type    | Required | Description      |
| ----------- | ------ | ------- | -------- | ---------------- |
| Bearer      | header | string  | true     | Access token     |
| projectId   | path   | integer | true     | Project id       |
| sectionId   | path   | integer | true     | Section id       |
| name        | body   | string  | true     | Task name        |
| description | body   | string  | true     | Task description |
| priority    | body   | string  | true     | Task priority    |
| completed   | body   | boolean | true     | Task completed   |
| dueDate     | body   | string  | false    | Task due date    |

**Request Body Example:**

```json
{
  "name": "New Task",
  "description": "Task description",
  "completed": false,
  "priority": "Medium"
}
```

**Response Example:**

```json
{
  "success": true,
  "status": "201",
  "message": "Task created",
  "data": {
    "id": 1,
    "sectionId": 1,
    "name": "Finish REST API",
    "description": "Create documentation and tests",
    "priority": "Low",
    "completed": false,
    "dueDate": "2023-10-09T12:00:00",
    "createdBy": "Sigmund",
    "createdAt": "2024-10-01T12:12:32"
  }
}
```

**Responses:**

| Status Code | Description          |
| ----------- | -------------------- |
| 201         | Task created         |
| 400         | Bad request          |
| 422         | Unprocessable entity |
| 401         | Unauthorized         |
| 403         | Forbidden            |

---

#### Get task by id

**GET** `/api/v1/projects/{projectId}/sections/{sectionId}/tasks/{taskId}`

| Parameter | In     | Type    | Required | Description  |
| --------- | ------ | ------- | -------- | ------------ |
| Bearer    | header | string  | true     | Access token |
| projectId | path   | integer | true     | Project id   |
| sectionId | path   | integer | true     | Section id   |
| taskId    | path   | integer | true     | Task id      |

**Response Example:**

```json
{
  "success": true,
  "status": "200",
  "message": "Task by id",
  "data": {
    "id": 1,
    "sectionId": 1,
    "name": "Finish REST API",
    "description": "Create documentation and tests",
    "priority": "Low",
    "completed": false,
    "dueDate": "2023-10-09T12:00:00",
    "createdBy": "Sigmund",
    "createdAt": "2024-10-01T12:12:32"
  }
}
```

**Responses:**

| Status Code | Description  |
| ----------- | ------------ |
| 200         | Task by id   |
| 400         | Bad request  |
| 404         | Not found    |
| 401         | Unauthorized |
| 403         | Forbidden    |

---

#### Delete task by id

**DELETE** `/api/v1/projects/{projectId}/sections/{sectionId}/tasks/{taskId}`

| Parameter | In     | Type    | Required | Description  |
| --------- | ------ | ------- | -------- | ------------ |
| Bearer    | header | string  | true     | Access token |
| projectId | path   | integer | true     | Project id   |
| sectionId | path   | integer | true     | Section id   |
| taskId    | path   | integer | true     | Task id      |

**Response Example:**

```json
{
  "success": true,
  "status": "204",
  "message": "Task deleted"
}
```

**Responses:**

| Status Code | Description  |
| ----------- | ------------ |
| 204         | No content   |
| 400         | Bad request  |
| 404         | Not found    |
| 401         | Unauthorized |
| 403         | Forbidden    |

---

#### Update task by id

**PATCH** `/api/v1/projects/{projectId}/sections/{sectionId}/tasks/{taskId}`

| Parameter   | In     | Type    | Required | Description      |
| ----------- | ------ | ------- | -------- | ---------------- |
| Bearer      | header | string  | true     | Access token     |
| projectId   | path   | integer | true     | Project id       |
| sectionId   | path   | integer | true     | Section id       |
| taskId      | path   | integer | true     | Task id          |
| name        | body   | string  | false    | Task name        |
| description | body   | string  | false    | Task description |
| priority    | body   | string  | false    | Task priority    |
| completed   | body   | boolean | false    | Task completed   |
| dueDate     | body   | string  | false    | Task due date    |

**Request Body Example:**

```json
{
  "name": "Updated Task",
  "description": "Updated description",
  "completed": true,
  "priority": "High"
}
```

**Response Example:**

```json
{
  "success": true,
  "status": "200",
  "message": "Task updated",
  "data": {
    "id": 1,
    "sectionId": 1,
    "name": "Finish REST API",
    "description": "Create documentation and tests",
    "priority": "Medium",
    "completed": true,
    "dueDate": "2023-10-09T12:00:00",
    "createdBy": "Sigmund",
    "createdAt": "2024-10-01T12:12:32"
  }
}
```

**Responses:**

| Status Code | Description          |
| ----------- | -------------------- |
| 200         | Task updated         |
| 400         | Bad request          |
| 422         | Unprocessable entity |
| 404         | Not found            |
| 401         | Unauthorized         |
| 403         | Forbidden            |

## Projekto išvados

- Semestro metu buvo sukurta užduočių valdymo sistema, kuri leidžia užsiregistravusiems naudotojams kurti projektus, sekcijas ir užduotis.
- Sistemos architektūra susideda iš kliento pusės, kuri realizuota naudojant `SvelteKit` karkasą, serverio pusės, kuri realizuota naudojant `Ktor` karkasą, ir duomenų bazės, kuri yra kuriama naudojant `PostgreSQL` duomenų bazę.
- Naudotojo sąsajos prototipai sukurti naudojant `Figma` įrankį. Sukurti prototipai leido pagreitinti svetainės UI realizavimo procesą.
- API specifikacija aprašyta naudojant `OpenAPI` standartą iš kurio galima, kokie endpoint'ai yra, kokie parametrai reikalingi ir kokie atsakymai grąžinami.
- Šio projekto kūrimas padėjo pritaikyti modulio metu įgytas teorines žinias praktiškai bei susipažinti su naujomis technologijomis ir įrankiais.
