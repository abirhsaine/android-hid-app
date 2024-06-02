package com.example.redeemers_faz_com.nav


sealed class NavRoute(val path: String) {

    object Home: NavRoute("home")
    object BluetoothPage: NavRoute("bluetooth")
    object BoardPage : NavRoute("board")

}
