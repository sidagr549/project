# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2017-01-16 14:49
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('donation', '0014_group_amount_required'),
    ]

    operations = [
        migrations.AlterField(
            model_name='group',
            name='amount_required',
            field=models.DecimalField(blank=True, decimal_places=2, max_digits=18, null=True),
        ),
        migrations.AlterField(
            model_name='group',
            name='max_deposit_per_person',
            field=models.DecimalField(decimal_places=2, default=100.0, max_digits=18),
        ),
        migrations.AlterField(
            model_name='group',
            name='min_amount_req',
            field=models.DecimalField(decimal_places=2, max_digits=18),
        ),
    ]
