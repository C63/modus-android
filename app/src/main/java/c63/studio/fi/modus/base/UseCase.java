package c63.studio.fi.modus.base;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValue> {

    private Q mRequestValues;

    public Q getRequestValues() {
        return mRequestValues;
    }

    public void setRequestValues(Q requestValues) {
        mRequestValues = requestValues;
    }

    protected abstract Observable<P> executeUseCaseInScheduler(Scheduler scheduler);

    /**
     * Data passed to a request.
     */
    public interface RequestValues {
    }

    /**
     * Data received from a request.
     */
    public interface ResponseValue {
    }
}
