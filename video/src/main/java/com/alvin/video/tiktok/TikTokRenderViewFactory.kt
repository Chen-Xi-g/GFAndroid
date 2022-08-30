package com.alvin.video.tiktok

import android.content.Context
import xyz.doikki.videoplayer.render.IRenderView
import xyz.doikki.videoplayer.render.RenderViewFactory
import xyz.doikki.videoplayer.render.TextureRenderView

class TikTokRenderViewFactory : RenderViewFactory() {
    override fun createRenderView(context: Context): IRenderView {
        return TikTokRenderView(TextureRenderView(context))
    }

    companion object {
        fun create(): TikTokRenderViewFactory {
            return TikTokRenderViewFactory()
        }
    }
}