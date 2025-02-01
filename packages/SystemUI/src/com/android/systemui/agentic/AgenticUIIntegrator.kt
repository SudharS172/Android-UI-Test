package com.android.systemui.agentic

import android.content.Context
import android.graphics.PointF

class AgenticUIIntegrator(context: Context) {
    private val aiCoreService = AICoreService(context)
    private val dynamicSurfaces = DynamicSurfacesLayout(context)
    private val inputHandler = MultimodalInputHandler(context)

    fun initialize() {
        aiCoreService.onCreate()
        dynamicSurfaces.setup()
        inputHandler.startListening()
    }

    fun handleUserInteraction(event: InteractionEvent) {
        when (event) {
            is VoiceCommand -> aiCoreService.handleVoiceCommand(event.audio)
            is Gesture -> dynamicSurfaces.handleGesture(event.type)
            is Touch -> inputHandler.processTouch(event.point)
        }
    }

    sealed class InteractionEvent {
        data class VoiceCommand(val audio: ByteArray) : InteractionEvent()
        data class Gesture(val type: MultimodalInputHandler.GestureType) : InteractionEvent()
        data class Touch(val point: PointF) : InteractionEvent()
    }
}
