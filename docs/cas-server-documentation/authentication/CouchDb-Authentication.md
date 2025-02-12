---
layout: default
title: CAS - CouchDb Authentication
category: Authentication
---

{% include variables.html %}

# CouchDb Authentication

Verify and authenticate credentials against a [CouchDb](http://couchdb.apache.org/) instance
via pac4j. CAS will automatically create the design documents required by pac4j.
Support is enabled by including the following dependency in the WAR overlay:

<div class="alert alert-warning">:warning: <strong>Usage</strong>
<p><strong>This feature is deprecated and is scheduled to be removed in the future</strong>.</p>
</div>


{% include_cached casmodule.html group="org.apereo.cas" module="cas-server-support-couchdb-authentication" %}

{% include_cached casproperties.html properties="cas.authn.couch-db" %}
