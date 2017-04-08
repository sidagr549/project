# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2017-01-15 16:09
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('donation', '0013_remove_group_slug'),
    ]

    operations = [
        migrations.AddField(
            model_name='group',
            name='amount_required',
            field=models.DecimalField(blank=True, decimal_places=2, max_digits=10, null=True),
        ),
    ]