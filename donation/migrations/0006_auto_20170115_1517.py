# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2017-01-15 09:47
from __future__ import unicode_literals

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('donation', '0005_group_description'),
    ]

    operations = [
        migrations.RenameField(
            model_name='group',
            old_name='contri_req',
            new_name='min_amount_req',
        ),
        migrations.RemoveField(
            model_name='group',
            name='deadline',
        ),
    ]
