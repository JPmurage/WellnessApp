package com.prince.myapplication

import android.content.Intent
//import android.media.tv.AdRequest
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView


import  com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import  com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback


class MainActivity : AppCompatActivity() {

//    The variable is only accessible inside this class
//    A variable to store the ad once it loads

    private var mInterstitialAd: InterstitialAd?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this)
        val adView=findViewById<AdView>(R.id.adView)
        val adRequest= AdRequest.Builder().build()
        adView.loadAd(adRequest)

//        loads the interstitial ad
        loadInterstitialAd()



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        Healthy recipe intent

//        finding the views from the layout using their ids

//        create a variable to store the buttons

        val recipe=findViewById<Button>(R.id.recipe)

//        set on click listener

        recipe.setOnClickListener {
//            write your intents

            val recipeIntent= Intent(applicationContext, HealthyRecipes::class.java)
            startActivity(recipeIntent)
        }
//        nutrition intent

        val nutrition=findViewById<Button>(R.id.nutrition)

        nutrition.setOnClickListener{

            val nutritionIntent=Intent(applicationContext,NutritionAdvice::class.java)
            startActivity(nutritionIntent)



        }
//    meditation intent

        val meditation=findViewById<Button>(R.id.meditation)

        meditation.setOnClickListener{

            val meditationIntent= Intent(applicationContext, Meditation::class.java)
            startActivity(meditationIntent)
        }
//        hydration alert

        val hydration=findViewById<Button>(R.id.hydration)
        hydration.setOnClickListener {

            val hydrationIntent=Intent(applicationContext, HydrationAlert::class.java )
        startActivity(hydrationIntent)

//            displays the ad
            showInterstitialAd()
        }

//        start exercise

        val exercise=findViewById<Button>(R.id.exercise)

        exercise.setOnClickListener {

            val exerciseIntent= Intent(applicationContext, StartExercise::class.java  )

            startActivity(exerciseIntent)
        }

//        Daily Motivation

        val motivation=findViewById<Button>(R.id.motivation)
        motivation.setOnClickListener {

            val motivationIntent=Intent(applicationContext, DailyMotivation::class.java)
        startActivity(motivationIntent)
        }
//        Weekly goals

        val goals=findViewById<Button>(R.id.goals)
        goals.setOnClickListener {

            val goalsIntent= Intent(applicationContext, WeeklyGoals::class.java)
            startActivity(goalsIntent)
        }


        val progress=findViewById<Button>(R.id.progress)
        progress.setOnClickListener {

            val progressIntent=Intent(applicationContext, CheckProgress::class.java)
            startActivity(progressIntent)
        }

    }


    fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712", // Test ID
            adRequest,
            object : InterstitialAdLoadCallback() {

                override fun onAdLoaded(ad: InterstitialAd) {
                    mInterstitialAd = ad
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    mInterstitialAd = null
                }
            }
        )
    }
    //Show Interstitial ad
    fun showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        }
    }

}