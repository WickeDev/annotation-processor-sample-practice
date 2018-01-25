package net.codesanctum.apt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.codesanctum.apt.vo.GeneratedTestModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GeneratedTestModel()
    }
}
