DESCRIPTION = "The goal of the Pango project is to provide an \
Open Source framework for the layout and rendering of \
internationalized text."
SECTION = "devel/libs"
LICENSE = "LGPL"
DEPENDS = "glib-2.0 fontconfig freetype zlib cairo-directfb-multi"
RPROVIDES_${PN} = "pango-cairo"
RREPLACES_${PN} = "pango"
PR = "r1"

SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/pango/1.28/pango-${PV}.tar.gz \
           file://Makefile.patch"
SRC_URI[md5sum] = "22ad1c8d3fda7e73b0798035f3dd96bc"
SRC_URI[sha256sum] = "3406952ac4c6882a2e5ccb9bdb591478edafaba5b7a671f01a46e2fe95f84c5b"

inherit autotools pkgconfig

S = "${WORKDIR}/pango-${PV}"

EXTRA_AUTORECONF = ""
EXTRA_OECONF += "--disable-glibtest \
                 --enable-explicit-deps=no \
                 --disable-debug \
                 --without-x"

PACKAGES_DYNAMIC = "pango-module-*"
PACKAGES_DYNAMIC_virtclass-native = ""

FILES_${PN} = "/etc ${bindir}/* ${libdir}/libpango*.so.*"
FILES_${PN}-dbg += "${libdir}/pango/${LIBV}/modules/.debug"
FILES_${PN}-dev += "${libdir}/pango/${LIBV}/modules/*.la"

python populate_packages_prepend () {
        prologue = bb.data.getVar("postinst_prologue", d, 1)
        modules_root = bb.data.expand('${libdir}/pango/${LIBV}/modules', d)
        do_split_packages(d, modules_root, '^pango-(.*)\.so$', 'pango-module-%s', 'Pango module %s', prologue + 'pango-querymodules > /etc/pango/pango.modules')
}

BBCLASSEXTEND = "native"

LEAD_SONAME = "libpango-1.0*"

LIBV = "1.6.0"

# seems to go wrong with default cflags
# FULL_OPTIMIZATION_arm = "-O2"

postinst_prologue() {
if [ "x$D" != "x" ]; then
  exit 1
fi
}
