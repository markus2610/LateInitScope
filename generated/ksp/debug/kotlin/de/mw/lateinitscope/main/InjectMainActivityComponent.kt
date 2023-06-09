package de.mw.lateinitscope.main

import android.app.Activity
import de.mw.lateinitscope.inject.ApplicationComponent
import de.mw.lateinitscope.inject.ComposeScreens
import kotlin.reflect.KClass

public fun KClass<MainActivityComponent>.create(activity: Activity,
    applicationComponent: ApplicationComponent): MainActivityComponent =
    InjectMainActivityComponent(activity, applicationComponent)

public fun KClass<MainActivityComponent>.create(activity: Activity): MainActivityComponent =
    InjectMainActivityComponent(activity)

public class InjectMainActivityComponent : MainActivityComponent {
  public override val composeScreens: ComposeScreens
    get() = ComposeScreens(
      greeting = { arg0 ->
        Greeting(
          name = arg0,
          lazyCycleBar = applicationComponent.bar
        )
      }
    )

  public constructor(activity: Activity, applicationComponent: ApplicationComponent) :
      super(activity, applicationComponent)

  public constructor(activity: Activity) : super(activity = activity)
}
