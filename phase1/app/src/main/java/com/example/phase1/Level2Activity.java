package com.example.phase1;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class Level2Activity extends GameManager {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    currPlayer = intent.getIntExtra("com.example.phase1.SEND_PLAYER", 0);
    // Set our window to fullscreen without the bar at the top.
    this.getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    // Remove the title.
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);

    setContentView(R.layout.n_activity_level2);

    //Change how hero component based on users choice
    final pl.droidsonroids.gif.GifImageView hero = findViewById(R.id.hero);
    if (getCharacter() == 0) {
      hero.setImageResource(R.drawable.run2);
    } else if (getCharacter() == 1) {
      hero.setImageResource(R.drawable.run1);
    }


    // Move the two copies of the front background image, continuously.
    final ImageView backgroundOne = findViewById(R.id.grass);
    final ImageView backgroundTwo = findViewById(R.id.grass1);
    final ImageView backgroundThree = findViewById(R.id.vegetation);
    final ImageView backgroundFour = findViewById(R.id.vegetation2);
    final ImageView backgroundFive = findViewById(R.id.tree);
    final ImageView backgroundSix = findViewById(R.id.rock1);
    final ImageView backgroundSeven = findViewById(R.id.tree2);
    final ImageView backgroundEight = findViewById(R.id.tree1_c);
    final ImageView backgroundNine = findViewById(R.id.rock1_c);
    final ImageView backgroundTen = findViewById(R.id.tree2_c);

    final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
    animator.setRepeatCount(ValueAnimator.INFINITE);
    animator.setInterpolator(new LinearInterpolator());
    animator.setDuration(5000L);
    animator.addUpdateListener(
        new ValueAnimator.AnimatorUpdateListener() {
          @Override
          public void onAnimationUpdate(ValueAnimator animation) {
            final float progress = (float) animation.getAnimatedValue();
            final float width1 = backgroundOne.getWidth();
            final float width2 = backgroundThree.getWidth();
            final float translationX1 = width1 * progress;
            final float translationX2 = width2 * progress - 10;
            backgroundOne.setTranslationX(-translationX1);
            backgroundTwo.setTranslationX(-translationX1 + width1);
            backgroundThree.setTranslationX(-translationX2);
            backgroundFour.setTranslationX(-translationX2 + width2);
            backgroundFive.setTranslationX(-translationX1);
            backgroundSix.setTranslationX(-translationX1 + width1);
            backgroundSeven.setTranslationX(-translationX2);
            backgroundEight.setTranslationX(-translationX2 + width2);
            backgroundNine.setTranslationX(-translationX1);
            backgroundTen.setTranslationX(-translationX1 + width1);
          }
        });
    animator.start();
  }

  // Visually show that the hero is jumping.
  public void tapScreen(View view) {
    final ImageView heroCharacter = findViewById(R.id.hero);

    final ObjectAnimator animationUp =
        ObjectAnimator.ofFloat(heroCharacter, "translationY", 0f, -180f, -200f, -180f, -100f, 0f);
    animationUp.setDuration(800L);
    animationUp.start();
  }
}
