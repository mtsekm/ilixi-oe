DESCRIPTION = "DirectFB is a thin library that provides developers \
with hardware graphics acceleration, input device handling and \
abstraction, an integrated windowing system with support for \
translucent windows and multiple display layers on top of the \
Linux framebuffer device."
HOMEPAGE = "http://directfb.org"
SECTION = "libs"
LICENSE = "LGPL"
DEPENDS = "jpeg libpng freetype zlib tslib sysfsutils"
RDEPENDS = "linux-fusion"
RPROVIDES_${PN} = "directfb-multi"
RREPLACES_${PN} = "directfb"
PR = "r5"

SRC_URI = "http://directfb.org/downloads/Core/DirectFB-1.4/DirectFB-${PV}.tar.gz \
           file://fix-pkgconfig-cflags.patch \
           file://mkdfiff.patch \
           file://dont-use-linux-config.patch"

SRC_URI[md5sum] = "94735ccec21120794adcce93a61445d2"
SRC_URI[sha256sum] = "85e27aa6ab9e784689a803961724eb5674cb9f5153770e63f02bf3f75a573a02"

S = "${WORKDIR}/DirectFB-${PV}"

inherit autotools binconfig pkgconfig

EXTRA_OECONF = "\
  --enable-freetype \
  --enable-zlib \
  --disable-libmpeg3 \
  --disable-sdl \
  --disable-vnc \
  --disable-x11 \
  --enable-multi \
  --with-smooth-scaling \
  --with-inputdrivers=tslib \ 
# choose graphics drivers here...
#  --with-gfxdrivers=omap \
"

RV = "1.4-5"

FILES_${PN}-dbg += "\
  ${libdir}/directfb-${RV}/*/*/.debug/*.so \
  ${libdir}/directfb-${RV}/*/.debug/*.so \
"
FILES_${PN}-dev += "\
  ${bindir}/directfb-config \
  ${libdir}/directfb-${RV}/systems/*.la \
  ${libdir}/directfb-${RV}/inputdrivers/*.la \
  ${libdir}/directfb-${RV}/interfaces/*/*.la \
  ${libdir}/directfb-${RV}/wm/*.la \
"
FILES_${PN} += "\
  ${libdir}/directfb-${RV}/systems/*.so \
  ${libdir}/directfb-${RV}/inputdrivers/*.so \
  ${libdir}/directfb-${RV}/interfaces/*/*.so \
  ${libdir}/directfb-${RV}/wm/*.so \
  ${datadir}/directfb-${PV} \
"

LEAD_SONAME = "libdirectfb-1.4.so.5"

