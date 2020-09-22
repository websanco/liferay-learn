#!/usr/bin/env python

import argparse
import functools
import os
import pandas as pd
import subprocess
import sys


def parse_mtimes(lines):
    mtimes = {}
    current_mtime = None

    for line in lines:
        if line == '':
            continue

        if line.find('\t') == -1:
            current_mtime = int(line)
            continue

        status, status_message = line.split('\t', 1)

        old_file_name = None
        new_file_name = None

        if status[0] == 'D':
            old_file_name = status_message
        elif status[0] == 'A':
            new_file_name = status_message
        elif status[0] == 'R':
            old_file_name, new_file_name = status_message.split('\t')

        if old_file_name is not None:
            del mtimes[old_file_name]

        if new_file_name is not None:
            mtimes[new_file_name] = current_mtime

    return mtimes


def get_mtimes(commit, folder):
    cwd = os.getcwd()

    pipe = subprocess.Popen([
        'git', 'log', '--reverse', '--name-status', '--pretty=%ct', commit,
        '--', folder
    ], cwd=cwd, stdout=subprocess.PIPE, stderr=subprocess.PIPE)

    out, err = pipe.communicate()

    return parse_mtimes(out.decode('UTF-8', 'replace').strip().split('\n'))


def is_tracked_mtime(source_language_path, mtime_info):
    file_name = mtime_info[0]

    if file_name.find(source_language_path) == -1:
        return False

    extension = file_name[file_name.rfind('.'):]

    if extension != '.markdown' and extension != '.md' and extension != '.rst':
        return False

    return True


def get_language_mtimes(commit, folder, language):
    language_path = '/%s/' % language

    mtimes = get_mtimes(commit, folder)

    is_tracked_language_mtime = functools.partial(
        is_tracked_mtime, language_path)

    return dict(filter(is_tracked_language_mtime, mtimes.items()))


def generate_report(args):
    source_language_path = '/en/'
    target_language_path = '/%s/' % args.target_language

    report = []

    if args.source_commit is not None:
        old_mtimes = get_language_mtimes(args.source_commit, args.folder, 'en')
    else:
        old_mtimes = get_language_mtimes('HEAD', args.folder, 'ja')

    new_mtimes = get_language_mtimes('upstream/master', args.folder, 'en')

    for file_name, mtime in new_mtimes.items():
        target_file_name = file_name.replace(
            source_language_path, target_language_path)

        if not os.path.exists(target_file_name):
            report.append({
                'file_name': file_name,
                'translation_status': 'missing'
            })

    for file_name, mtime in old_mtimes.items():
        translation_status = None

        if args.source_commit is not None:
            source_file_name = file_name
        else:
            source_file_name = file_name.replace(
                target_language_path, source_language_path)

        if source_file_name not in new_mtimes:
            translation_status = 'orphaned'
        elif mtime < new_mtimes[source_file_name]:
            translation_status = 'outdated'
        else:
            continue

        report.append({
            'file_name': file_name,
            'translation_status': translation_status
        })

    report = sorted(report, key=lambda x: x['file_name'])

    df = pd.DataFrame.from_dict(report)
    df.to_csv('translation_report_%s.csv' % args.target_language, index=False)


if __name__ == '__main__':
    parser = argparse.ArgumentParser(
        description='Generates a translation report as a CSV file.'
    )

    parser.add_argument('folder', help='folder to validate')
    parser.add_argument(
        'target_language', help='translation target language')
    parser.add_argument(
        'source_commit', nargs='?', default=None,
        help='reference commit used by translators (used for up-to-date ' +
             'checks, defaults to translation modified time if not set)')

    args = parser.parse_args()

    generate_report(args)
