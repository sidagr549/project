# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2017-01-15 10:53
from __future__ import unicode_literals

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('donation', '0007_deposit'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='deposit',
            name='group',
        ),
        migrations.RemoveField(
            model_name='deposit',
            name='user',
        ),
        migrations.AddField(
            model_name='group',
            name='deposit',
            field=models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, related_name='groupdeposits', to='donation.Deposit'),
        ),
        migrations.AddField(
            model_name='userprofile',
            name='deposit',
            field=models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, related_name='userdeposits', to='donation.Deposit'),
        ),
    ]
