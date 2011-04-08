DESCRIPTION = "Cairo graphics library"
SECTION = "libs"
LICENSE = "MPL LGPL"
DEPENDS = "pixman libsm libpng fontconfig glib-2.0 directfb-multi"
RPROVIDES_${PN} = "cairo-directfb"
RREPLACES_${PN} = "cairo"
PR = "r1"

SRC_URI = "http://cairographics.org/releases/cairo-${PV}.tar.gz"
SRC_URI[md5sum] = "f101a9e88b783337b20b2e26dfd26d5f"
SRC_URI[sha256sum] = "32018c7998358eebc2ad578ff8d8559d34fc80252095f110a572ed23d989fc41"

S = "${WORKDIR}/cairo-${PV}"

inherit autotools pkgconfig

#check for TARGET_FPU=soft and inform configure of the result so it can disable some floating points
def get_cairo_fpu_setting(bb, d):
  if bb.data.getVar('TARGET_FPU', d, 1) in [ 'soft' ]:
    return "--disable-some-floating-point"
  return ""

EXTRA_OECONF = "${@get_cairo_fpu_setting(bb, d)} \
                 --enable-directfb \
                 --disable-xlib \
                 --disable-xlib-xrender \
                 --disable-win32"

PACKAGES =+ "cairo-trace cairo-trace-dev libcairo-script-interpreter libcairo-gobject"
FILES_libcairo-gobject = "${libdir}/libcairo-gobject.so.*"
FILES_libcairo-script-interpreter = "${libdir}/libcairo-script-interpreter.so.*"
FILES_cairo-trace = "${bindir}/*trace ${libdir}/cairo/libcairo-trace.so.*"
FILES_cairo-trace-dev = "${libdir}/cairo/*"
CFLAGS_append += " -I${STAGING_INCDIR}/directfb"
LDFLAGS_append += " -ldirectfb"
