package de.mw.lateinitscope.inject

import de.mw.lateinitscope.main.Greeting
import me.tatarka.inject.annotations.Inject

@Inject
class ComposeScreens(val greeting: Greeting)
