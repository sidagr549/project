# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2017-01-15 13:50
from __future__ import unicode_literals

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('donation', '0012_remove_deposit_deposit_id'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='group',
            name='slug',
        ),
    ]
