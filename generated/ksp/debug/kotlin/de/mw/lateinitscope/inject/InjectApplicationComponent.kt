package de.mw.lateinitscope.inject

import android.app.Application
import de.mw.lateinitscope.LazyCycleBar
import kotlin.reflect.KClass
import me.tatarka.inject.`internal`.LazyMap
import me.tatarka.inject.`internal`.ScopedComponent

public fun KClass<ApplicationComponent>.create(application: Application): ApplicationComponent =
    InjectApplicationComponent(application)

public class InjectApplicationComponent(
  application: Application
) : ApplicationComponent(application), ScopedComponent {
  public override val _scoped: LazyMap = LazyMap()

  public override val bar: LazyCycleBar
    get() = _scoped.get("de.mw.lateinitscope.LazyCycleBar") {
      run {
        lateinit var lazyCycleBar: LazyCycleBar
        bar(
          foo = lazy {
            _scoped.get("de.mw.lateinitscope.LazyCycleFoo") {
              foo(
                bar = _scoped.get("de.mw.lateinitscope.LazyCycleBar") {
                  lazyCycleBar
                }
              )
            }
          }
        ).also {
          lazyCycleBar = it
        }
      }

    }
}
