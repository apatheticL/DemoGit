package com.nhatle.demosqlline;

public interface IStore {
    Topic getTopic(int position);
    Store getStore(int position);
    int getCount();
    void onClick(int position);

}
