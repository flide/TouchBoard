#!/bin/bash

adb shell monkey -p inc.flide.touchboard --pct-touch 20 --pct-motion 40 --pct-nav 5 --pct-majornav 5 --pct-syskeys 10 --pct-anyevent 20 -v 2000
