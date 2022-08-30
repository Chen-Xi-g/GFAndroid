package com.alvin.video.utils

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.view.ViewParent
import android.widget.FrameLayout
import xyz.doikki.videoplayer.controller.ControlWrapper
import xyz.doikki.videoplayer.player.VideoView
import xyz.doikki.videoplayer.player.VideoViewManager
import java.lang.reflect.Field


/**
 * <h3> 作用类描述：视频控件的Utils工具类</h3>
 *
 * @Package :        com.yleanlink.common_ui.utils
 * @Date :           2022/1/18-13:31
 * @author 高国峰
 */
object Utils {
    /**
     * 获取当前的播放核心
     */
    fun getCurrentPlayerFactory(): Any? {
        val config = VideoViewManager.getConfig()
        var playerFactory: Any? = null
        try {
            val mPlayerFactoryField: Field = config.javaClass.getDeclaredField("mPlayerFactory")
            mPlayerFactoryField.isAccessible = true
            playerFactory = mPlayerFactoryField.get(config)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return playerFactory
    }

    fun getCurrentPlayerFactoryInVideoView(controlWrapper: ControlWrapper): Any? {
        var playerFactory: Any? = null
        try {
            val mPlayerControlField = controlWrapper.javaClass.getDeclaredField("mPlayerControl")
            mPlayerControlField.isAccessible = true
            val playerControl = mPlayerControlField[controlWrapper]
            if (playerControl is VideoView) {
                playerFactory = getCurrentPlayerFactoryInVideoView(playerControl as VideoView)
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return playerFactory
    }

    fun getCurrentPlayerFactoryInVideoView(videoView: VideoView): Any? {
        var playerFactory: Any? = null
        try {
            val mPlayerFactoryField: Field = videoView.javaClass.getDeclaredField("mPlayerFactory")
            mPlayerFactoryField.isAccessible = true
            playerFactory = mPlayerFactoryField[videoView]
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return playerFactory
    }

    /**
     * 将View从父控件中移除
     */
    fun removeViewFormParent(v: View?) {
        if (v == null) return
        val parent: ViewParent? = v.parent
        if (parent is FrameLayout) {
            parent.removeView(v)
        }
    }

    /**
     * Returns a string containing player state debugging information.
     */
    fun playState2str(state: Int): String? {
        val playStateString: String
        playStateString = when (state) {
            VideoView.STATE_IDLE -> "idle"
            VideoView.STATE_PREPARING -> "preparing"
            VideoView.STATE_PREPARED -> "prepared"
            VideoView.STATE_PLAYING -> "playing"
            VideoView.STATE_PAUSED -> "pause"
            VideoView.STATE_BUFFERING -> "buffering"
            VideoView.STATE_BUFFERED -> "buffered"
            VideoView.STATE_PLAYBACK_COMPLETED -> "playback completed"
            VideoView.STATE_ERROR -> "error"
            else -> "idle"
        }
        return String.format("playState: %s", playStateString)
    }

    /**
     * Returns a string containing player state debugging information.
     */
    fun playerState2str(state: Int): String? {
        val playerStateString: String
        playerStateString = when (state) {
            VideoView.PLAYER_NORMAL -> "normal"
            VideoView.PLAYER_FULL_SCREEN -> "full screen"
            VideoView.PLAYER_TINY_SCREEN -> "tiny screen"
            else -> "normal"
        }
        return String.format("playerState: %s", playerStateString)
    }

    /**
     * Gets the corresponding path to a file from the given content:// URI
     *
     * @param context    Context
     * @param contentUri The content:// URI to find the file path from
     * @return the file path as a string
     */
    @SuppressLint("Range")
    fun getFileFromContentUri(context: Context, contentUri: Uri?): String? {
        if (contentUri == null) {
            return null
        }
        if (ContentResolver.SCHEME_FILE == contentUri.getScheme()) {
            return contentUri.toString()
        }
        var filePath: String? = null
        val filePathColumn =
            arrayOf(MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME)
        val contentResolver: ContentResolver = context.getContentResolver()
        val cursor: Cursor? = contentResolver.query(
            contentUri, filePathColumn, null,
            null, null
        )
        if (cursor != null) {
            cursor.moveToFirst()
            filePath = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA))
            cursor.close()
        }
        return filePath
    }
}
