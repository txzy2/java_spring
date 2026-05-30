.PHONY: r

r:
	./gradlew bootRun

up-dev:
	docker compose -f docker-compose.dev.yml up -d

down-dev:
	docker compose -f docker-compose.dev.yml down

lz:
	lazydocker
