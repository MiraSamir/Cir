package model;

public interface ISavingObservable {

  public void addObserver(ISavingObserver o);

  public void removeObserver(ISavingObserver o);

}
