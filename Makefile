.PHONY: r

r:
	./gradlew bootRun

up-dev:
	docker compose -f compose.dev.yml up -d

down-dev:
	docker compose -f compose.dev.yml down

lz:
	lazydocker
