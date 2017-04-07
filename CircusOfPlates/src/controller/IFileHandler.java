package controller;

import model.SavingMemento;

public interface IFileHandler {

  public SavingMemento load();

  public void save(SavingMemento memento);

}
