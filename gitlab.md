# Git og GitLab

I INF101 bruker du som student **Git** til

- klone (laste ned) oppgavene fra en Git-server til din egen datamaskin,
- committe (lagre) så ofte som mulig mens du jobber med en oppgave,
- pulle (laste ned de siste endringene fra serveren til maskinen din) og
- pushe (laste opp de siste endringene fra maskinen til serveren).
- ++

Eclipse er i stand til å gjøre mange av Git-operasjonene inne i programmet, men vi anbefaler at du først lærer deg å bruke Git ved hjelp av terminalen for å få en bedre forståelse av hvordan det fungerer. Hvis ikke kan alt virke "magisk", og når noe går galt (merk **når**) vet man ikke hva man skal gjøre.

Applikasjonen vi bruker til å håndtere alle Git-repositoriene heter **GitLab**, og kjører på en UiB-server på URLen Retting.ii.uib.no. (Eksempler på andre slike servere er github.com, gitlab.com, bitbucket.com, etc.)

## 1. Tilgang til GitLab

Alle studentene som er oppmeldt til INF101 og har logget inn på Mitt UiB skal automatisk ha fått en konto på GitLab-instansen på Retting.ii.uib.no. Merk at når vi snakker om GitLab i INF101 så er det alltid på denne URLen. Brukernavnet ditt er det samme som student-epostadressen din, f.eks. bruce.wayne@student.uib.no. Dersom du er usikker på hva som er din studentepostadresse finner du dette oppe til høyre inne på din Webmail. For å sette et passord velger du "Forgot your password?" på framsiden -> skriver inn eposten -> åpner eposten fra UiB -> Reset Password -> Lag et passord.

Bruk gjerne et minutt eller to til å gjøre deg kjent på nettsiden etter du har logget inn, ettersom du kommer til å måtte navigere deg rundt mange ganger. Søkevinduet oppe til høyre er nyttig når du vet hvilket repositorie du vil gå til.

## 2. Legg inn opplysninger i Git

Bruk de to terminalkommandoene

`> git config --global user.name "FIRST_NAME LAST_NAME"`
`> git config --global user.email "MY_NAME@example.com"`

til å lagre navn og epost (bruk student-eposten din her) i Git-konfigurasjonen på maskinen din. Hvis du ikke gjør dette kommer Git til å klage når du prøver å committe (lagre) endringer, for Git er nemlig opptatt av _hvem_ som gjorde endringene slik at de potensielt senere kan krediteres (eller skyldes på...).

## 3. Clone

På GitLab kommer du til å få tilgang til en del **repositorier** (prosjekt-mapper). Når du skal kjøre eller utvikle videre på et repositorie må du først hente det ned på din egen maskin ved hjelp av Git, i Git-verdenen kalles dette å **klone** repositoriet. Navnet kommer av at du oppretter en kopi av repositoriet på maskinen din.

Noen av repositoriene kommer til å være offentlige slik at alle har tilgang til de, og noen kommer til å være private som f.eks. ukesoppgavene og innleveringene. Dine medstudenter har med andre ord ingen tilgang til dine oppgaver. For å kunne klone et _privat_ prosjekt kommer Git til å spørre deg om brukernavn og passord. Dersom du etterhvert blir lei dette kan du bruke dette svaret på Stack Overflow til å lagre passordet permanent: https://stackoverflow.com/a/5343146. Da lar du Git bruke et eksternt program til lagre passordene, f.eks. _osxkeychain_ som er preinstallert på mac.

Når du skal klone et repositorie går du inn på framsiden til repositorie på GitLab. Merk at du kan ha tilgang til to repositorier med samme navn, et offentlig (under f.eks inf101.v20.oppgaver) som du ikke har redigeringstilgang til og et privat - i så fall ønsker du å klone det _private_ repositoriet. Trykk på den blå Clone-knappen oppe til høyre og kopier URLen for _HTTPS_ (i skrivende stund er SSH stengt). Gå så til terminalen din og naviger deg til den mappen du vil klone repositoriet til. Klon med kommandoen

`git clone <URL her>`

f.eks

`git clone https://retting.ii.uib.no/inf101/inf101.v20/inf101.v20.lab1.git`

Git skal så laste ned repositoriet til den mappen du er i.

## 4. Commit

For å committe en endring må de endrede filene først _stages_. Hvis du bruker kommandoen `git status` mens du befinner deg inne i mappen, så får du se hvilke filer som har blitt endret, og om de er staged (grønt filnavn) eller ikke (rødt filnavn). For å stage en fil kan du bruke kommandoen `git add <fil>`, der fil er den filen du vil stage. Gjør dette med alle filene du vil ha med i committen.

For å committe skriver du `git commit -m "<Melding her>"`, med en melding som passer til de siste endringene. Det er konvensjon å bruke presens i meldingen, slik at den svarer på spørsmålet "Hvilke endring gjør denne committen?".

Eks: "Implementerer Ball-klassen sin konstruktør", .

_Commit-meldingen skal kun beskrive én endring. Hvis du vil ha med ordet "og" i meldingen, eller har gjort så mange endringer i committen at du ikke klarer å komme på en passende endrings-melding, så er dette et tegn på at du ikke committer ofte nok._

## 5. Push

På samme måte som det er god vane å committe ofte er det også god vane å pushe ofte. Dette gjelder spesielt når man jobber med andre, slik at man ikke får to versjoner av prosjektet som divergerer fra hverandre.

For å pushe bruker du `git push`. Det er viktig at du leser den meldingen som printes til terminalen, for du kan få [Rejected]. Da betyr det at noen andre (F.eks. den som laget oppgaven eller en gruppeleder) har pushet en commit som du ikke har lokalt. Dette løser du enkelt ved å pulle (se neste del).

## 6. Pull

Dersom serveren har committer som du ikke har lokalt, må du bruke `git pull` etter du har committet alle endringene dine. Dette går vanligvis uten problemer, men dersom du har gjort endringer i _samme_ fil på _samme_ plass som committen du akkurat hentet ned, så får du en _merge conflict_.

Får du en merge conflict så får du også en liste med filer det er konflikt i. I disse filene så vil du finne en blokk som starter med `<<<<<<<` og ender med `>>>>>>>`. Inne i denne blokken så finner du både din endring og endringen på serveren, seperert med `=======`.

```
I denne tekstfilen ønsker jeg å formidle
<<<<<<< HEAD
hva som skjer hvis det oppstår en konflikt.
=======
hvordan en fil ser ut etter en konflikt.
>>>>>>> branch-a
```

For å løse konflikten må du gå inn i de filene det gjelder og "reparere" de ved å fjerne de endringene du ikke vil beholde, og fjerne de tre sepereringslinjene (`>>>>>>>`, etc.).

Når du har løst en konflikt så har du også gjort en endring, så nå må du committe før du fortsetter å jobbe. Merk at du i dette tilfellet ikke trenger en commit-melding, det holder nå å skrive `git commit`.

Skulle Git likevel ønske en commit-melding så vil den åpne standard teksteditor i terminalen. Git forventer nå at du skriver en melding **øverst** i filen som åpnes, og at filen blir lagret og lukket. Hvis teksteditoren er _vim_ (hvis ikke du kan vim så bør du [endre til nano](https://superuser.com/a/503845)), så går du inn i skrivemodus ved å trykke på `i`, går ut av skrivemodus ved å trykke på `esc`, og lagrer og avslutter ved å skrive inn `:wq` og trykke på Enter.

## 7. Flere resurser

Sel om GIT er et verktøy du kan bruke og teste på lokal maskin er det ikke alltid like lett å følge utfallet av komandoene. Dersom du ønsker å lære GIT er det mange gode videoer og
ressurser online. Under er en liste som kan være til hjelp:

- [LearnGitBranching](https://learngitbranching.js.org/) er et online "spill" hvor du kan prøve ut og øve på GIT kommandoene
