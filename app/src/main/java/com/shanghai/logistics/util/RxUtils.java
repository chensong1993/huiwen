package com.shanghai.logistics.util;

import android.util.Log;

import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.models.entity.ApiDataResponse;
import com.shanghai.logistics.models.entity.ApiResponse;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.single.SingleToFlowable;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<T, T> rxSchedulerHelperObserable() {    //compose简化线程
        return new ObservableTransformer<T, T>() {
            @Override
            public Observable<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> SingleTransformer<T, T> rxSchedulerHelperSingle() {    //compose简化线程
        return new SingleTransformer<T, T>() {
            @Override
            public Single<T> apply(Single<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<ApiResponse<T>, T> handleResults() {   //compose判断结果
        return new FlowableTransformer<ApiResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<ApiResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<ApiResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(ApiResponse<T> tHttpResponse) {
                        if (tHttpResponse.getMsg() == 1) {
                            Log.i("apply", "apply: ");
                            return createData(tHttpResponse.getData());

                        } else {
                            return Flowable.error(new Exception(tHttpResponse.getMsg() + ""));
                        }
                    }
                });
            }
        };
    }


    /**
     * 只有data数组
     */
    public static <T> FlowableTransformer<ApiDataResponse<T>, T> dataHandleResults() {   //compose判断结果
        return new FlowableTransformer<ApiDataResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<ApiDataResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<ApiDataResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(ApiDataResponse<T> tHttpResponse) {
                        Log.i("apply", "apply: ");
                        return createData(tHttpResponse.getData());

                    }
                });
            }
        };
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> handleNoResults() {   //compose判断结果
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<T, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(T tHttpResponse) {
                        Log.i("apply", "apply: ");
                        return createData(tHttpResponse);

                    }
                });
            }
        };
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> SingleTransformer<ApiResponse<T>, T> handleResultFile() {   //compose判断结果
        return new SingleTransformer<ApiResponse<T>, T>() {
            @Override
            public SingleSource<T> apply(Single<ApiResponse<T>> upstream) {
                if (upstream.blockingGet().getMsg() == 1) {
                    Log.i("apply", "apply: ");
                    return createSingle(upstream.blockingGet().getData());
                } else {
                    return Single.error(new Exception(upstream.blockingGet().getMsg() + ""));
                }

            }


        };
    }

    public static <T> SingleTransformer<T, T> getList() {   //compose判断结果
        return new SingleTransformer<T, T>() {
            @Override
            public Single<T> apply(Single<T> upstream) {
                return upstream.flatMap(new Function<T, Single<T>>() {
                    @Override
                    public Single<T> apply(T tHttpResponse) {
                        return createSingle(tHttpResponse);
                    }
                });
            }
        };
    }

//    public static <T> ObservableTransformer<ApiResponse<T>, T> handleResultsObserable() {   //compose判断结果
//        return new ObservableTransformer<ApiResponse<T>, T>() {
//            @Override
//            public Observable<T> apply(Observable<ApiResponse<T>> httpResponseFlowable) {
//                return httpResponseFlowable.flatMap(new Function<ApiResponse<T>, Observable<T>>() {
//                    @Override
//                    public Observable<T> apply(ApiResponse<T> tHttpResponse) {
//                        if (tHttpResponse.getCurPage() > 0) {
//                            return createObservable(tHttpResponse.getResults());
//                        } else {
//                            return Observable.error(new Exception("服务器返回error : " + tHttpResponse.getCurPage()));
//                        }
//                    }
//                });
//            }
//        };
//    }


//    public static <T> SingleTransformer<ApiResponse<List<T>>, T> getFirst() {   //compose判断结果
//        return new SingleTransformer<ApiResponse<List<T>>, T>() {
//            @Override
//            public Single<T> apply(Single<ApiResponse<List<T>>> httpResponseFlowable) {
//                return httpResponseFlowable.flatMap(new Function<ApiResponse<List<T>>, Single<T>>() {
//                    @Override
//                    public Single<T> apply(ApiResponse<List<T>> tHttpResponse) {
//                        if (tHttpResponse.getCurPage() > 0) {
//                            return null;
////                            return Single.fromFuture(.getResults().get(0));
//                        } else {
//                            return Single.error(new Exception("服务器返回error : " + tHttpResponse.getCurPage()));
//                        }
//                    }
//                });
//            }
//        };
//    }


    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

    public static <T> Observable<T> createObservable(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }

        });
    }

    public static <T> Single<T> createSingle(final T t) {
        return Single.create(new SingleOnSubscribe<T>() {
            @Override
            public void subscribe(SingleEmitter<T> emitter) throws Exception {
                try {
                    emitter.onSuccess(t);
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }

        });
    }

}
