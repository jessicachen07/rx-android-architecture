package com.tehmou.rxbookapp.data.provider;

import android.net.Uri;

import com.tehmou.rxandroidarchitecture.contract.DatabaseContract;
import com.tehmou.rxandroidarchitecture.contract.SerializedJsonContract;
import com.tehmou.rxandroidarchitecture.provider.ContractContentProviderBase;
import com.tehmou.rxandroidarchitecture.route.DatabaseRouteBase;

import rx.functions.Func1;

/**
 * Created by ttuo on 22/03/15.
 */
public class GithubContentProvider extends ContractContentProviderBase {
    public static final String PROVIDER_NAME = "com.tehmou.rxbookapp.data.provider.GithubContentProvider";
    private static final String DATABASE_NAME = "database";
    private static final int DATABASE_VERSION = 12;

    public GithubContentProvider() {
        final Func1<Uri, String> getWhereFuncInteger =
                uri -> SerializedJsonContract.ID + " = " + uri.getLastPathSegment();
        final Func1<Uri, String> getWhereFuncString =
                uri -> SerializedJsonContract.ID + " = '" + uri.getLastPathSegment() + "'";
        final String defaultSortOrder = SerializedJsonContract.ID + " ASC";

        DatabaseContract gitHubRepositoryContract = new GitHubRepositoryContract();
        addDatabaseContract(gitHubRepositoryContract);
        addDatabaseRoute(
                new DatabaseRouteBase.Builder(gitHubRepositoryContract.getTableName())
                        .setMimeType("vnd.android.cursor.item/vnd.tehmou.android.rxbookapp.repository")
                        .setDefaultSortOrder(defaultSortOrder)
                        .setGetWhereFunc(getWhereFuncInteger)
                        .setPath(gitHubRepositoryContract.getTableName() + "/*")
                        .build());

        DatabaseContract gitHubRepositorySearchContract = new GitHubRepositorySearchContract();
        addDatabaseContract(gitHubRepositorySearchContract);
        addDatabaseRoute(
                new DatabaseRouteBase.Builder(gitHubRepositorySearchContract.getTableName())
                        .setMimeType("vnd.android.cursor.item/vnd.tehmou.android.rxbookapp.repositorysearch")
                        .setDefaultSortOrder(defaultSortOrder)
                        .setGetWhereFunc(getWhereFuncString)
                        .setPath(gitHubRepositorySearchContract.getTableName() + "/*")
                        .build());

        DatabaseContract userSettingsContract = new UserSettingsContract();
        addDatabaseContract(userSettingsContract);
        addDatabaseRoute(
                new DatabaseRouteBase.Builder(userSettingsContract.getTableName())
                        .setMimeType("vnd.android.cursor.item/vnd.tehmou.android.rxbookapp.usersettings")
                        .setDefaultSortOrder(defaultSortOrder)
                        .setGetWhereFunc(getWhereFuncInteger)
                        .setPath(userSettingsContract.getTableName() + "/*")
                        .build());

        DatabaseContract networkRequestStatusContract = new NetworkRequestStatusContract();
        addDatabaseContract(networkRequestStatusContract);
        addDatabaseRoute(
                new DatabaseRouteBase.Builder(networkRequestStatusContract.getTableName())
                        .setMimeType("vnd.android.cursor.item/vnd.tehmou.android.rxbookapp.networkrequeststatus")
                        .setDefaultSortOrder(defaultSortOrder)
                        .setGetWhereFunc(getWhereFuncInteger)
                        .setPath(networkRequestStatusContract.getTableName() + "/*")
                        .build());
    }

    @Override
    protected String getProviderName() {
        return PROVIDER_NAME;
    }

    @Override
    protected String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    protected int getDatabaseVersion() {
        return DATABASE_VERSION;
    }
}
