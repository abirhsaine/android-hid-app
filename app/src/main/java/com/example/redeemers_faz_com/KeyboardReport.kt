package com.example.redeemers_faz_com

import android.view.KeyEvent

@JvmInline
value class KeyboardReport(
    val bytes: ByteArray = ByteArray(3) { 0 }
) {

    fun setModifiers(list: List<Int>) {
        val value = list.fold(0) { i,j -> i or j } and 0b1111_1111
        bytes[0] = value.toByte()
    }

    var key1: Byte
        get() = bytes[2]
        set(value) { bytes[2] = value }

    fun reset() = bytes.fill(0)

    companion object {
        const val ID = 8
        val KeyEventMap = mapOf(
            KeyEvent.KEYCODE_A to 20,
            KeyEvent.KEYCODE_B to 5,
            KeyEvent.KEYCODE_C to 6,
            KeyEvent.KEYCODE_D to 7,
            KeyEvent.KEYCODE_E to 8,
            KeyEvent.KEYCODE_F to 9,
            KeyEvent.KEYCODE_G to 10,
            KeyEvent.KEYCODE_H to 11,
            KeyEvent.KEYCODE_I to 12,
            KeyEvent.KEYCODE_J to 13,
            KeyEvent.KEYCODE_K to 14,
            KeyEvent.KEYCODE_L to 15,
            KeyEvent.KEYCODE_M to 51,
            KeyEvent.KEYCODE_N to 17,
            KeyEvent.KEYCODE_O to 18,
            KeyEvent.KEYCODE_P to 19,
            KeyEvent.KEYCODE_Q to 4,
            KeyEvent.KEYCODE_R to 21,
            KeyEvent.KEYCODE_S to 22,
            KeyEvent.KEYCODE_T to 23,
            KeyEvent.KEYCODE_U to 24,
            KeyEvent.KEYCODE_V to 25,
            KeyEvent.KEYCODE_Z to 26,
            KeyEvent.KEYCODE_X to 27,
            KeyEvent.KEYCODE_Y to 28,
            KeyEvent.KEYCODE_W to 29,


            KeyEvent.KEYCODE_1 to 30,
            KeyEvent.KEYCODE_2 to 31,
            KeyEvent.KEYCODE_3 to 32,
            KeyEvent.KEYCODE_4 to 33,
            KeyEvent.KEYCODE_5 to 34,
            KeyEvent.KEYCODE_6 to 35,
            KeyEvent.KEYCODE_7 to 36,
            KeyEvent.KEYCODE_8 to 37,
            KeyEvent.KEYCODE_9 to 38,
            KeyEvent.KEYCODE_0 to 39,

            KeyEvent.KEYCODE_SPACE to 44,

            KeyEvent.KEYCODE_F1 to 58,
            KeyEvent.KEYCODE_F2 to 59,
            KeyEvent.KEYCODE_F3 to 60,
            KeyEvent.KEYCODE_F4 to 61,
            KeyEvent.KEYCODE_F5 to 62,
            KeyEvent.KEYCODE_F6 to 63,
            KeyEvent.KEYCODE_F7 to 64,
            KeyEvent.KEYCODE_F8 to 65,
            KeyEvent.KEYCODE_F9 to 66,
            KeyEvent.KEYCODE_F10 to 67,
            KeyEvent.KEYCODE_F11 to 68,
            KeyEvent.KEYCODE_F12 to 69,

            KeyEvent.KEYCODE_MINUS to 35,
            KeyEvent.KEYCODE_LEFT_BRACKET to 34,
            KeyEvent.KEYCODE_RIGHT_BRACKET to 45,
            KeyEvent.KEYCODE_COMMA to 16,
            KeyEvent.KEYCODE_SEMICOLON to 54,
            // special key for FR MAC keyboard '@', spend a while to figure it out
            KeyEvent.KEYCODE_AT to 100,
        )
    }
}