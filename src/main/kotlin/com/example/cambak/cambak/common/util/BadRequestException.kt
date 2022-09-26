package com.example.cambak.cambak.common.util

import java.lang.RuntimeException


class BadRequestException(var code: Int) : RuntimeException(){}