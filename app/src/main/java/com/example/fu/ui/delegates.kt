package com.example.fu.ui

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

sealed class RecyclerItem {

    data class GarbageType(
        val id: Int,
        val name: String,
    ) : RecyclerItem()

    object Loading : RecyclerItem()

    object Error : RecyclerItem()
}


fun notificationListDelegate(
) =
    adapterDelegateViewBinding<String, Any, ItemNotificationsListBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemNotificationsListBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        },
        block = {
            fun bindItem(notification: NotificationEntity) {
                if (notification.viewed)
                    binding.notification.setBackgroundResource(R.color.white)
                else
                    binding.notification.setBackgroundResource(R.color.notification_not_viewed)
                binding.notification.setNotificationMarkInfo(
                    notification.info.value ?: 0,
                    notification.info.subject ?: "",
                    getString(R.string.rated),
                    notification.info.type ?: "",
                    notification.info.setMarkDate
                )
            }

            bind { bindItem(item) }

        }
    )

fun loadingDelegate() =
    adapterDelegateViewBinding<LoadingVO, Any, ItemLoadingBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemLoadingBinding.inflate(layoutInflater, parent, false)
        }, block = { }
    )

fun pushDisabled(
    clickListener: () -> Unit
) = adapterDelegateViewBinding<PushDisabled, Any, ItemPushDisabledBinding>(
    viewBinding = { layoutInflater, parent ->
        ItemPushDisabledBinding.inflate(layoutInflater, parent, false)
    }, block = {
        bind {
            itemView.setOnClickListener {
                clickListener()
            }
        }
    }
)

