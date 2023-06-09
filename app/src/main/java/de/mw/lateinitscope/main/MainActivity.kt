package de.mw.lateinitscope.main

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.mw.lateinitscope.inject.ApplicationComponent
import de.mw.lateinitscope.inject.ComposeScreens
import de.mw.lateinitscope.ui.theme.LateInitScopeTheme
import de.mw.lateinitscope.LazyCycleBar
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Inject
import me.tatarka.inject.annotations.Provides

class MainActivity : ComponentActivity() {

    private lateinit var component: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component = MainActivityComponent::class.create(this)

        setContent {
            LateInitScopeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    component.composeScreens.greeting("Android")
                }
            }
        }
    }
}



@Component
abstract class MainActivityComponent(
    @get:Provides val activity: Activity,
    @Component val applicationComponent: ApplicationComponent = ApplicationComponent.from(activity)
) {
    abstract val composeScreens: ComposeScreens
}

typealias Greeting = @Composable (name: String) -> Unit

@Inject
@Composable
fun Greeting(@Assisted name: String, lazyCycleBar: LazyCycleBar) {
    val foo = lazyCycleBar.foo.value
    Greeting(name, modifier = Modifier)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LateInitScopeTheme {
        Greeting("Android")
    }
}