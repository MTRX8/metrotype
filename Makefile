.PHONY: icons clean upgrade

icons:
	@echo "Generating launcher icons..."
	@dart run flutter_launcher_icons

clean:
	@echo "Cleaning project..."
	@flutter clean
	@flutter pub get

upgrade:
	@echo "Upgrading dependencies..."
	@flutter clean
	@flutter pub get
	@flutter pub upgrade
	@flutter pub upgrade --major-versions
