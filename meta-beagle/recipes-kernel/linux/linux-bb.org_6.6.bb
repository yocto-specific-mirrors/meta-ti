# Look in the generic major.minor directory for files
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.6:"

SECTION = "kernel"
SUMMARY = "BeagleBoard.org Linux kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

COMPATIBLE_MACHINE = "beagle.*"

inherit kernel

require recipes-kernel/linux/setup-defconfig.inc
require recipes-kernel/linux/ti-kernel.inc

DEPENDS += "gmp-native libmpc-native"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} ${EXTRA_DTC_ARGS}"

# Extra DT overlays/capes
KERNEL_DEVICETREE:append:armv7a = " \
${DT_VENDOR_PREFIX}AM335X-PRU-UIO-00A0.dtbo \
${DT_VENDOR_PREFIX}BB-ADC-00A0.dtbo \
${DT_VENDOR_PREFIX}BB-BBBW-WL1835-00A0.dtbo \
${DT_VENDOR_PREFIX}BB-BBGG-WL1835-00A0.dtbo \
${DT_VENDOR_PREFIX}BB-BBGW-WL1835-00A0.dtbo \
${DT_VENDOR_PREFIX}BB-BONE-4D5R-01-00A1.dtbo \
${DT_VENDOR_PREFIX}BB-BONE-eMMC1-01-00A0.dtbo \
${DT_VENDOR_PREFIX}BB-BONE-LCD4-01-00A1.dtbo \
${DT_VENDOR_PREFIX}BB-BONE-NH7C-01-A0.dtbo \
${DT_VENDOR_PREFIX}BB-CAPE-DISP-CT4-00A0.dtbo \
${DT_VENDOR_PREFIX}BB-HDMI-TDA998x-00A0.dtbo \
${DT_VENDOR_PREFIX}BB-I2C1-MCP7940X-00A0.dtbo \
${DT_VENDOR_PREFIX}BB-I2C1-RTC-DS3231.dtbo \
${DT_VENDOR_PREFIX}BB-I2C1-RTC-PCF8563.dtbo \
${DT_VENDOR_PREFIX}BB-I2C2-BME680.dtbo \
${DT_VENDOR_PREFIX}BB-I2C2-MPU6050.dtbo \
${DT_VENDOR_PREFIX}BB-LCD-ADAFRUIT-24-SPI1-00A0.dtbo \
${DT_VENDOR_PREFIX}BB-NHDMI-TDA998x-00A0.dtbo \
${DT_VENDOR_PREFIX}BBORG_COMMS-00A2.dtbo \
${DT_VENDOR_PREFIX}BBORG_FAN-A000.dtbo \
${DT_VENDOR_PREFIX}BBORG_RELAY-00A2.dtbo \
${DT_VENDOR_PREFIX}BB-SPIDEV0-00A0.dtbo \
${DT_VENDOR_PREFIX}BB-SPIDEV1-00A0.dtbo \
${DT_VENDOR_PREFIX}BB-UART1-00A0.dtbo \
${DT_VENDOR_PREFIX}BB-UART2-00A0.dtbo \
${DT_VENDOR_PREFIX}BB-UART4-00A0.dtbo \
${DT_VENDOR_PREFIX}BB-W1-P9.12-00A0.dtbo \
${DT_VENDOR_PREFIX}BONE-ADC.dtbo \
${DT_VENDOR_PREFIX}M-BB-BBG-00A0.dtbo \
${DT_VENDOR_PREFIX}M-BB-BBGG-00A0.dtbo \
${DT_VENDOR_PREFIX}PB-MIKROBUS-0.dtbo \
${DT_VENDOR_PREFIX}PB-MIKROBUS-1.dtbo \
"

S = "${WORKDIR}/git"

# 6.6.32 version for 32-bit
SRCREV:armv7a = "525684d28fadc2bdae5424f28b219377bd5d4639"
PV:armv7a = "6.6.32+git"
BRANCH:armv7a = "v6.6.32-ti-arm32-r7"

# 6.6.58 version for 64-bit
SRCREV:aarch64 = "9beffa9162f6a0f5de7e25bbb66026a579376428"
PV:aarch64 = "6.6.58+git"
BRANCH:aarch64 = "v6.6.58-ti-arm64-r16"

KERNEL_GIT_URI = "git://github.com/beagleboard/linux.git"

SRC_URI += "file://no-fortify.cfg"
KERNEL_CONFIG_FRAGMENTS += "${UNPACKDIR}/no-fortify.cfg"