package com.alvin.base_core.base.livedata;

/**
 * 解决数据倒灌
 * https://github.com/KunMinX/UnPeek-LiveData
 */
public class EventLiveData<T> extends ProtectedUnPeekLiveData<T> {

    public EventLiveData() {
        super();
    }

    @Override
    public void setValue(T value) {
        super.setValue(value);
    }

    @Override
    public void postValue(T value) {
        super.postValue(value);
    }

    public static class Builder<T> {

        /**
         * 是否允许传入 null value
         */
        private boolean isAllowNullValue;

        public Builder<T> setAllowNullValue(boolean allowNullValue) {
            this.isAllowNullValue = allowNullValue;
            return this;
        }

        public EventLiveData<T> create() {
            EventLiveData<T> liveData = new EventLiveData<>();
            liveData.isAllowNullValue = this.isAllowNullValue;
            return liveData;
        }
    }
}