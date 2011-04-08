# ilixi image

IMAGE_PREPROCESS_COMMAND = "create_etc_timestamp"

ANGSTROM_EXTRA_INSTALL ?= ""

ZZAPSPLASH = ' ${@base_contains("MACHINE_FEATURES", "screen", "psplash-zap", "",d)}'

IMAGE_LINGUAS = ""

DEPENDS = " \
  task-base \
  ${ANGSTROM_EXTRA_INSTALL} \
  ${SPLASH} \
  ${ZZAPSPLASH} \
  task-base-extended \
  linux-fusion \
  directfb-multi \
  cairo-directfb-multi \
  pango-cairo \
  libsigc++-2.0 \
  libxml2 \
  ilixi-ui \
  ttf-freefonts \
"

IMAGE_INSTALL = " \
  task-base \
  ${ANGSTROM_EXTRA_INSTALL} \
  ${SPLASH} \
  ${ZZAPSPLASH} \
  task-base-extended \
  linux-fusion \
  directfb-multi \
  cairo-directfb-multi \
  pango-cairo \
  libsigc++-2.0 \
  libxml2 \
  ilixi-ui \
  ttf-freefonts \
  tslib \
"

inherit image

export IMAGE_BASENAME = "ilixi-image"
