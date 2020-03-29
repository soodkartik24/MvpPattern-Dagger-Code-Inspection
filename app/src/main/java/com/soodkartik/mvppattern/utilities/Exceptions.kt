package com.soodkartik.mvppattern.utilities

import java.io.IOException

class NoInternetException(message: String?) : IOException(message)
class NoServerFoundException(message: String?) : IOException(message)