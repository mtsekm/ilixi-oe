DESCRIPTION = "Fusion is an Inter-Process-Communication (IPC) kernel module with a high level of abstraction. It offers \
primitives like skirmishes (locks), calls, properties and shared memory pools."
HOMEPAGE = "http://www.directfb.org"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "GPLv2"
RDEPENDS_${PN} = "kernel-${KERNEL_VERSION}"
DEPENDS = "virtual/kernel"

S = "${WORKDIR}/linux-fusion-${PV}"

SRC_URI = "http://directfb.org/downloads/Core/linux-fusion/${P}.tar.gz \
           file://40-fusion.rules"

inherit module

do_install () {
        # install module
        install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/drivers/char/fusion
        install -m 0644 ${S}/linux/drivers/char/fusion/fusion*${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${KERNEL_VERSION}/drivers/char/fusion
        # install header
        install -d ${D}${includedir}/linux
        install -m 644 ${S}/linux/include/linux/fusion.h ${D}${includedir}/linux
        # add udev rule
        install -d ${D}/${sysconfdir}/udev/rules.d
        install -m 0644 ${WORKDIR}/40-fusion.rules ${D}${sysconfdir}/udev/rules.d/
}

MAKE_TARGETS = "KERNEL_BUILD=${STAGING_KERNEL_DIR} MAKE='make -e'"

FILES_${PN} = "${base_libdir}/modules/${KERNEL_VERSION}/drivers/char/fusion/*${KERNEL_OBJECT_SUFFIX}"
FILES_${PN} += "${sysconfdir}/"
