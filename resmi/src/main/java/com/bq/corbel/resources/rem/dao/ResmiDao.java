package com.bq.corbel.resources.rem.dao;

import com.bq.corbel.lib.queries.request.*;
import com.bq.corbel.resources.rem.model.GenericDocument;
import com.bq.corbel.resources.rem.model.ResourceUri;
import com.bq.corbel.resources.rem.resmi.exception.InvalidApiParamException;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.index.Index;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author Francisco Sánchez - Rubén Carrasco
 */
public interface ResmiDao {

    boolean existsResources(ResourceUri uri);

    JsonObject findResource(ResourceUri uri);

    JsonArray findCollection(ResourceUri uri, Optional<List<ResourceQuery>> resourceQueries, Optional<Pagination> pagination,
            Optional<Sort> sort) throws InvalidApiParamException;

    JsonElement findRelation(ResourceUri uri, Optional<List<ResourceQuery>> resourceQueries, Optional<Pagination> pagination,
            Optional<Sort> sort) throws InvalidApiParamException;

    JsonArray findCollectionWithGroup(ResourceUri uri, Optional<List<ResourceQuery>> resourceQueries, Optional<Pagination> pagination,
                                      Optional<Sort> sort, List<String> groups, boolean first) throws InvalidApiParamException;

    JsonArray findDistinctFields(ResourceUri uri, Optional<List<ResourceQuery>> resourceQueries, Optional<Pagination> pagination,
            Optional<Sort> sort,  String field) throws InvalidApiParamException;

    JsonArray findRelationWithGroup(ResourceUri uri, Optional<List<ResourceQuery>> resourceQueries, Optional<Pagination> pagination,
                                    Optional<Sort> sort, List<String> groups, boolean first) throws InvalidApiParamException;

    void updateCollection(ResourceUri uri, JsonObject jsonObject, List<ResourceQuery> resourceQueries);

    void updateResource(ResourceUri uri, JsonObject entity);

    boolean conditionalUpdateResource(ResourceUri uri, JsonObject jsonObject, List<ResourceQuery> resourceQueries);

    void saveResource(ResourceUri uri, Object entity);

    void createRelation(ResourceUri uri, JsonObject jsonObject) throws NotFoundException;

    JsonObject deleteResource(ResourceUri uri);

    List<GenericDocument> deleteCollection(ResourceUri uri, Optional<List<ResourceQuery>> queries);

    List<GenericDocument> deleteRelation(ResourceUri uri, Optional<List<ResourceQuery>> queries);

    JsonElement count(ResourceUri resourceUri, List<ResourceQuery> resourceQueries);

    JsonElement average(ResourceUri resourceUri, List<ResourceQuery> resourceQueries, String field);

    JsonElement sum(ResourceUri resourceUri, List<ResourceQuery> resourceQueries, String field);

    JsonElement max(ResourceUri resourceUri, List<ResourceQuery> resourceQueries, String field);

    JsonElement min(ResourceUri resourceUri, List<ResourceQuery> resourceQueries, String field);

    JsonArray combine(ResourceUri resourceUri, Optional<List<ResourceQuery>> resourceQueries, Optional<Pagination> pagination,
                      Optional<Sort> sort, String field, String expression) throws InvalidApiParamException;

    JsonElement histogram(ResourceUri resourceUri, List<ResourceQuery> resourceQueries, Optional<Pagination> pagination,
                              Optional<Sort> sort, String field);

    void moveRelation(ResourceUri uri, RelationMoveOperation relationMoveOperation);

    <T> List<T> findAll(ResourceUri uri, Class<T> entityClass);

    void ensureExpireIndex(ResourceUri uri);

    void ensureIndex(ResourceUri uri, Index index);
}
