---
layout: default
title: CAS - YubiKey Authentication
category: Multifactor Authentication
---

{% include variables.html %}

# CouchDb YubiKey Registration

Support is enabled by including the following dependencies in the WAR overlay:

{% include_cached casmodule.html group="org.apereo.cas" module="cas-server-support-yubikey-couchdb" %}

<div class="alert alert-warning">:warning: <strong>Usage</strong>
<p><strong>This feature is deprecated and is scheduled to be removed in the future</strong>.</p>
</div>

{% include_cached casproperties.html properties="cas.authn.mfa.yubikey.couch-db" %}

The registration records are kept inside a single CouchDb database of your choosing that will be auto-created by CAS.
The structure of this database's documents is as follows:

| Field      | Description                                                      |
|------------|------------------------------------------------------------------|
| `id`       | Unique record identifier, acting as the primary key.             |
| `publicId` | The public identifier/key of the device used for authentication. |
| `username` | The username whose device is registered.                         |
