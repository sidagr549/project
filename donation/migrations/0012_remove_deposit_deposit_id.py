# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2017-01-15 13:11
from __future__ import unicode_literals

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('donation', '0011_group_max_deposit_per_person'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='deposit',
            name='deposit_id',
        ),
    ]