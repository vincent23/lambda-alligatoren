.PHONY: all

all:
	java -cp ../libs/gdx.jar:../tools/gdx-tools.jar com.badlogic.gdx.tools.imagepacker.TexturePacker2 packages ../assets
	mkdir -p ../assets
	find . -maxdepth 1 -type f -name '*.png' -exec cp {} ../assets/ \;
