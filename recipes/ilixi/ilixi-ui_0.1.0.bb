DESCRIPTION = "C++ UI toolkit utilising DirectFB."
HOMEPAGE = "http://www.ilixi.org"
SECTION = "libs"
LICENSE = "GPLv3"
PRIORITY = "required"
DEPENDS = "linux-fusion directfb-multi cairo-directfb-multi pango-cairo libsigc++-2.0 libxml2"
PR = "r0"

SRC_URI = "http://www.yefele.com/tarik/ilixi-${PV}.tar.gz \
  file://directfbrc \
  file://ilixi \
"

SRC_URI[md5sum] = "a62f956fecf4a56c59224138e41640c0"
SRC_URI[sha256sum] = "4645ab78ae69032f5d59c7721a6d037ed6006409530358941de8505e264e7c85"

S = "${WORKDIR}/ilixi-${PV}"

inherit autotools pkgconfig update-rc.d

INITSCRIPT_NAME = "ilixi"
INITSCRIPT_PARAMS = "defaults 99 1"

#EXTRA_OECONF = "--enable-logger"

FILES_${PN} += "${datadir}/ilixi-${PV}" 

do_install_append() {
  install -d ${D}${sysconfdir}
  install -m 0655 ${WORKDIR}/directfbrc ${D}${sysconfdir}/directfbrc
  install -d ${D}${sysconfdir}/init.d
  install -m 0655 ${WORKDIR}/ilixi ${D}${sysconfdir}/init.d/ilixi
  chmod 755 ${D}${sysconfdir}/init.d/ilixi
}

