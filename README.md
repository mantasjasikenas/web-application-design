<div align="center">
  <img src="./docs/assets/logo.svg" alt="Shortcuts logo" width="75">  
  
  <h1>Tornado - užduočių valdymo sistema</h1>
</div>

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

Projektas ⇒ Sekcija ⇒ Užduotis

- Projektas (project)
- Sekcija (section)
- Užduotis (task)
- Naudotojas (user)

## Sistemos architektūra

### Sistemos sudedamosios dalys

- Kliento pusė (angl. Front-end) – bus realizuojama naudojant `SvelteKit` karkasą. SvelteKit - tai Svelte paremtas
  karkasas, kuris remiasi Svelte pagrindais ir suteikia galingų funkcijų, tokių kodo skaidymas, failų maršrutizavimas, kurios palengvina sudėtingų programų kūrimą.
- Serverio pusė (angl. Back-end) – bus realizuojama naudojant `Ktor` karkasą. Ktor yra asinchroninis karkasas, skirtas
  mikroservisams, žiniatinklio programoms ir kt. kurti, naudojant Kotlin programavimo kalbą.
- Duomenys bus saugomi `PostgreSQL` duomenų bazėje, kuri bus pasiekiama per `Exposed ORM`. Duomenų bazė yra reliacinė,
  kuri leidžia saugoti duomenis lentelėse bei sudaryti ryšius tarp jų.

## Diegimo diagrama

![alt text](./docs/assets/deployment_diagram.png)

## Naudotojo sąsajos projektas

Projektuojamos sąsajos langų wireframe`ai ir juos atitinkančios realizacijos langų iškarpos.

## API specifikacija

API specifikacija bus aprašyta naudojant `OpenAPI` standartą.

## Projekto išvados
