#!/usr/bin/env python3

import os
import sys
import subprocess
import argparse

# Fix the bug of ANSI colors on terminal for Windows terminals
os.system('')

parser = argparse.ArgumentParser()
parser.add_argument('args', nargs=argparse.REMAINDER)
args = parser.parse_args()

current_dir = os.path.dirname(os.path.realpath(__file__))

cmd = [ os.path.realpath(os.path.join(current_dir, '..', '..', 'scripts', 'upgrade-plugin-versions.py')) ]
cmd = cmd + [
	'--modules', os.path.realpath(os.path.join(current_dir, '..', 'modules.json')),
	'--pluginyml', os.path.realpath(os.path.join(current_dir, '..', 'bspl-bom', 'bspl-properties', 'plugins.yml')),
	'--bomproperties', os.path.realpath(os.path.join(current_dir, '..', 'bspl-bom', 'bspl-properties', 'bspl-lang.properties')),
	'--rootpath', os.path.realpath(os.path.join(current_dir, '..'))
]
cmd = cmd + args.args

#print(str(cmd))
completed = subprocess.run(cmd)

sys.exit(completed.returncode)

