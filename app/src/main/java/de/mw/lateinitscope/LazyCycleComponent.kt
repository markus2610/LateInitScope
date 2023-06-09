package de.mw.lateinitscope

import de.mw.lateinitscope.inject.ApplicationScope
import me.tatarka.inject.annotations.Provides

class LazyCycleFoo(val bar: LazyCycleBar)
class LazyCycleBar(val foo: Lazy<LazyCycleFoo>)


interface LazyCycleComponent {

    //val bar: LazyCycleBar

    @ApplicationScope
    @Provides fun bar(foo: Lazy<LazyCycleFoo>) = LazyCycleBar(foo)

    @ApplicationScope
    @Provides
    fun foo(bar: LazyCycleBar) = LazyCycleFoo(bar)
}