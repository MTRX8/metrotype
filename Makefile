.PHONY: icons upgrade

icons:
	@echo "Generating launcher icons..."
	@dart run flutter_launcher_icons

upgrade:
	@echo "Upgrading dependencies..."
	@flutter clean
	@flutter pub get
	@flutter pub upgrade
	@flutter pub upgrade --major-versions
