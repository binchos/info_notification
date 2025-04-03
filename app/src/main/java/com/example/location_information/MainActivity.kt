package com.example.location_information


import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabPost = findViewById<TextView>(R.id.rv46lcxztecl) // 게시글 탭
        val tabContent = findViewById<TextView>(R.id.rjexmhzvh5ek) // 콘텐츠 탭
        val bottomBar = findViewById<View>(R.id.rvkyyih317pd) // 하단 바

        // 초기 Fragment 설정
        replaceFragment(PostFragment())
        updateTabUI(tabPost, tabContent, bottomBar) // 초기 UI 설정

        // 게시글 버튼 클릭 시
        tabPost.setOnClickListener {
            replaceFragment(PostFragment())
            updateTabUI(tabPost, tabContent, bottomBar)
        }

        // 콘텐츠 버튼 클릭 시
        tabContent.setOnClickListener {
            replaceFragment(ContentFragment())
            updateTabUI(tabContent, tabPost, bottomBar)
        }
    }

    // Fragment 교체 함수
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    // UI 업데이트 함수
    private fun updateTabUI(selectedTab: TextView, otherTab: TextView, bottomBar: View) {
        selectedTab.setTextColor(Color.parseColor("#65558F")) // 활성화 색상
        otherTab.setTextColor(Color.parseColor("#49454F")) // 비활성화 색상

        // 선택된 부모 LinearLayout 가져오기
        val parentLayout = selectedTab.parent as View

        parentLayout.post {
            val targetX = parentLayout.x
            val targetWidth = parentLayout.width

            // 하단 바 애니메이션 적용
            ObjectAnimator.ofFloat(bottomBar, "translationX", targetX).apply {
                duration = 200
                start()
            }

            // 하단 바 너비 조정
            bottomBar.layoutParams.width = targetWidth
            bottomBar.requestLayout()
        }
    }

}