package com.ucb.app.github.presentation.state

sealed interface GithubEvent {
    object OnClickFind: GithubEvent
    data class OnChangeAvagar(
        val value: String
    ): GithubEvent
}