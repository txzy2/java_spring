### Run project

    prod: cp .env.example .env.prod
    dev: cp .env.example .env.dev

### Up

    make rp → (build prod)
    make rda → (
            docker compose -f compose.dev.yml --env-file .env.dev up -d &&\
            ./gradlew bootRun --args='--spring.profiles.active=dev'
    ) → /*
          * === Description: ===
          * build docker and up app on 8080 port 
          */
