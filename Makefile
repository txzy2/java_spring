.PHONY: rd

dud:
	docker compose -f compose.dev.yml --env-file .env.dev up -d

ddd:
	docker compose -f compose.dev.yml down

rda:
	@docker compose -f compose.dev.yml --env-file .env.dev up -d
	@sudo chmod -R 777 logs/
	@( \
		./gradlew classes --continuous & \
		CLASSES_PID=$$!; \
		trap 'kill $$CLASSES_PID; docker compose -f compose.dev.yml --env-file .env.dev down' INT TERM EXIT; \
		./gradlew bootRun --args="--spring.profiles.active=dev"; \
	)

rp:
	docker compose -f compose.prod.yml --env-file .env.prod down && \
    docker compose -f compose.prod.yml --env-file .env.prod up -d --build

lz:
	lazydocker

pal:
	docker logs -f --tail=50 spring_app

psp:
	docker compose -f compose.prod.yml --env-file .env.prod ps

psd:
	docker compose -f compose.dev.yml --env-file .env.dev ps
