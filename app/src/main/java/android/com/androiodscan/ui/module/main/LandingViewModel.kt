package android.com.androiodscan.ui.module.main

import android.app.Application
import android.com.androiodscan.data.ApiResponse
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class LandingViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel

    @Bindable
    var apiResponses : MutableLiveData<List<ApiResponse>> = MutableLiveData()


}
